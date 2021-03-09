package com.davmag.nearplaces.domain.repository

import androidx.paging.PagingData
import com.davmag.nearplaces.domain.model.Place
import io.reactivex.Flowable
import io.reactivex.Maybe

interface PlaceRepository {
    companion object {
        const val DEFAULT_RADIUS = 2000
    }

    fun get() : Flowable<PagingData<Place>>
    fun fetch(
        radius : Int = DEFAULT_RADIUS,
        openNow : Boolean = true,
        fetchNextPage : Boolean = false
    ) : Maybe<List<Place>>
}