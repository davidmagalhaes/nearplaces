package com.davmag.nearplaces.data.source.remote.contract

import com.davmag.nearplaces.domain.aggregate.PlaceSearchResult
import io.reactivex.Maybe

interface PlaceRemoteDatasource {
    fun fetch(
        latitude: Double,
        longitude: Double,
        radius: Int,
        type: PlaceType,
        openNow: Boolean,
        pageToken: String? = null
    ) : Maybe<PlaceSearchResult>
}