package com.davmag.nearplaces.data.source.remote.impl

import com.davmag.nearplaces.BuildConfig
import com.davmag.nearplaces.data.source.remote.api.PlacesApi
import com.davmag.nearplaces.data.source.remote.contract.PlaceRemoteDatasource
import com.davmag.nearplaces.data.source.remote.contract.PlaceType
import com.davmag.nearplaces.data.source.remote.mapper.PlaceFetchResultRemoteMapper
import com.davmag.nearplaces.domain.aggregate.PlaceSearchResult
import io.reactivex.Maybe

class PlaceRemoteDatasourceImpl(
    private val placesApi: PlacesApi
) : PlaceRemoteDatasource {
    override fun fetch(
        latitude: Double,
        longitude: Double,
        radius: Int,
        type: PlaceType,
        openNow: Boolean,
        pageToken: String?
    ) : Maybe<PlaceSearchResult> {
        val fetch = pageToken?.let {
            placesApi.fetch(
                key = BuildConfig.G_PLACES_API_KEY,
                location = "$latitude,$longitude",
                radius = radius,
                pageToken = pageToken
            )
        } ?: placesApi.fetch(
            key = BuildConfig.G_PLACES_API_KEY,
            location = "$latitude,$longitude",
            radius = radius,
            type = type.value,
            openNow = openNow,
            pageToken = pageToken
        )

        return fetch.map {
            PlaceFetchResultRemoteMapper.toEntity(it)
        }
    }
}
