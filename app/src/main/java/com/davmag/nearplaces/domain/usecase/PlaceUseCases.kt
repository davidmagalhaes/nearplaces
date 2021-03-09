package com.davmag.nearplaces.domain.usecase

import androidx.paging.PagingData
import com.davmag.nearplaces.domain.model.Place
import com.davmag.nearplaces.domain.repository.PlaceRepository
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GetPlacesUseCase {
    fun get() : Flowable<PagingData<Place>>
}

class GetPlacesUseCaseImpl(
    private val placeRepository: PlaceRepository
) : GetPlacesUseCase{
    override fun get(): Flowable<PagingData<Place>> {
        return placeRepository.get()
    }
}

interface FetchPlacesUseCase {
    fun fetch() : Maybe<Any>
}

class FetchPlaceUseCaseImpl(
    private val placeRepository: PlaceRepository
) : FetchPlacesUseCase{
    override fun fetch(): Maybe<Any> {
        return placeRepository.fetch(

        ).map { }
    }

}
