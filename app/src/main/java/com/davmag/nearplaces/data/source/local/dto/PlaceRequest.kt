package com.davmag.nearplaces.data.source.local.dto

data class PlaceRequest (
    val latitude: Double,
    val longitude: Double,
    val radius : Int
)