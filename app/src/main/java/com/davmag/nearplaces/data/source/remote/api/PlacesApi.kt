package com.davmag.nearplaces.data.source.remote.api

import com.davmag.nearplaces.data.source.remote.dto.PlaceFetchResult
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {

    @GET("place/nearbysearch/json")
    fun fetch(
        @Query("key") key : String,
        @Query("location") location: String? = null,
        @Query("radius") radius: Int? = null,
        @Query("type") type: String? = null,
        @Query("openNow") openNow: Boolean? = null,
        @Query("pagetoken") pageToken: String? = null
    ) : Maybe<PlaceFetchResult>

}