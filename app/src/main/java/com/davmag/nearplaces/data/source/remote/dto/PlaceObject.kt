package com.davmag.nearplaces.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class PlaceObject (
    @SerializedName("place_id")
    val id : String,
    val name : String,
    val types : List<String>
)