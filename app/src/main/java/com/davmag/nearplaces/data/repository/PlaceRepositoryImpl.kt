package com.davmag.nearplaces.data.repository

import androidx.paging.*
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.flowable
import com.davmag.nearplaces.data.source.local.contract.LocationLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.PlaceLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.UserContextLocalDatasource
import com.davmag.nearplaces.data.source.remote.util.SimpleRemoteMediator
import com.davmag.nearplaces.data.source.remote.contract.PlaceRemoteDatasource
import com.davmag.nearplaces.data.source.remote.contract.PlaceType
import com.davmag.nearplaces.domain.model.Place
import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.data.repository.scheduler.AppSchedulers
import com.davmag.nearplaces.domain.aggregate.PlaceSearchResult
import io.reactivex.Flowable
import io.reactivex.Maybe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.await
import retrofit2.HttpException
import java.net.HttpURLConnection

class PlaceRepositoryImpl(
    private val appSchedulers: AppSchedulers,
    private val userContextLocalDatasource: UserContextLocalDatasource,
    private val locationLocalDatasource: LocationLocalDatasource,
    private val placeLocalDatasource : PlaceLocalDatasource,
    private val placeRemoteDatasource : PlaceRemoteDatasource
) : PlaceRepository {

    companion object {
        const val PAGE_SIZE = 60
    }

    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    override fun get(): Flowable<PagingData<Place>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE
            ),
            1,
            SimpleRemoteMediator {
                fetch(
                    fetchNextPage = true
                ).onErrorReturn { e ->
                    if(e is HttpException && e.code() == HttpURLConnection.HTTP_NOT_FOUND)
                        emptyList()
                    else
                        throw e
                }
                .await()
            },
            placeLocalDatasource.get().asPagingSourceFactory(),
        ).flowable.cachedIn(GlobalScope)
            .observeOn(appSchedulers.database())
            .subscribeOn(appSchedulers.main())
    }

    override fun fetch(
        radius : Int,
        openNow: Boolean,
        fetchNextPage : Boolean
    ): Maybe<List<Place>> {
        return locationLocalDatasource.get()
            .observeOn(appSchedulers.database())
            .subscribeOn(appSchedulers.main())
            .firstElement()
            .flatMap { locationList ->
                val location = locationList.first()
                val resultList = arrayListOf<PlaceSearchResult>()

                userContextLocalDatasource.single()
                    .flatMap { userContext ->
                        placeRemoteDatasource.fetch(
                            longitude = location.longitude,
                            latitude = location.latitude,
                            type = PlaceType.RESTAURANT,
                            radius = radius,
                            openNow = openNow,
                            pageToken = userContext.restaurantPageToken
                                .takeIf { fetchNextPage }
                        ).flatMap {
                            resultList.add(it)
                            placeRemoteDatasource.fetch(
                                longitude = location.longitude,
                                latitude = location.latitude,
                                type = PlaceType.BAR,
                                radius = radius,
                                openNow = openNow,
                                pageToken = userContext.barPageToken
                                    .takeIf { fetchNextPage }
                            )
                        }.flatMap {
                            resultList.add(it)
                            placeRemoteDatasource.fetch(
                                longitude = location.longitude,
                                latitude = location.latitude,
                                type = PlaceType.CAFE,
                                radius = radius,
                                openNow = openNow,
                                pageToken = userContext.cafePageToken
                                    .takeIf { fetchNextPage }
                            )
                        }.map {
                            resultList.add(it)
                            resultList
                        }
                    }
            }
            .flatMap { fetchResults ->
                val places = fetchResults.map { it.places }.flatten()
                val store = if(fetchNextPage) placeLocalDatasource.append(places)
                        else placeLocalDatasource.cache(places)

                store.observeOn(appSchedulers.database())
                    .flatMap {
                        userContextLocalDatasource.single()
                    }
                    .flatMap {
                        userContextLocalDatasource.patch(
                            it.apply {
                                restaurantPageToken = fetchResults[0].nextPageToken
                                barPageToken = fetchResults[1].nextPageToken
                                cafePageToken = fetchResults[2].nextPageToken
                            }
                        )
                    }
                    .map {
                        places
                    }
            }
    }
}