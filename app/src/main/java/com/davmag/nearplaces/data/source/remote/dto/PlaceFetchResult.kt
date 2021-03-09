package com.davmag.nearplaces.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class PlaceFetchResult (
    @SerializedName("next_page_token")
    val nextPageToken : String?,
    val results : List<PlaceObject>,
    val status : String
)