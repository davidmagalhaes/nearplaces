package com.davmag.nearplaces.data.source.local.impl

import com.davmag.nearplaces.data.source.local.contract.LocationLocalDatasource
import com.davmag.nearplaces.domain.model.Location
import io.reactivex.Flowable

class LocationLocalDatasouceImpl : LocationLocalDatasource {
    override fun get(): Flowable<List<Location>> {
        return Flowable.just(listOf(Location(
            latitude = -33.8670522,
            longitude = 151.1957362
        )))
    }
}