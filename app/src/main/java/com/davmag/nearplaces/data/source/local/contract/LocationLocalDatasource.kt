package com.davmag.nearplaces.data.source.local.contract

import com.davmag.nearplaces.domain.model.Location
import io.reactivex.Flowable

interface LocationLocalDatasource {
    fun get() : Flowable<List<Location>>
}