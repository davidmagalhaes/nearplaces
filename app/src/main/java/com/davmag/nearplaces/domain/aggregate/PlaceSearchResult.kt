package com.davmag.nearplaces.domain.aggregate

import com.davmag.nearplaces.domain.model.Place

data class PlaceSearchResult (
    val nextPageToken: String?,
    val places : List<Place>
)