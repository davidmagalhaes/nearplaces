package com.davmag.nearplaces.data.source.local.contract

import androidx.paging.DataSource
import com.davmag.nearplaces.domain.model.Place
import io.reactivex.Maybe

interface PlaceLocalDatasource {
    fun get() : DataSource.Factory<Int, Place>
    fun append(places : List<Place>)  : Maybe<Any>
    fun cache(places : List<Place>) : Maybe<Any>
}