package com.davmag.nearplaces.data.source.local.impl

import androidx.paging.DataSource
import com.davmag.nearplaces.data.source.local.contract.PlaceLocalDatasource
import com.davmag.nearplaces.data.source.local.dao.PlacesDao
import com.davmag.nearplaces.data.source.local.mapper.PlacesLocalMapper
import com.davmag.nearplaces.domain.model.Place
import io.reactivex.Maybe

class PlaceLocalDatasourceImpl(
    private val placesDao: PlacesDao
) : PlaceLocalDatasource {
    override fun get(): DataSource.Factory<Int, Place> {
        return placesDao.get().mapByPage {
            PlacesLocalMapper.toEntity(it)
        }
    }

    override fun append(places: List<Place>): Maybe<Any> {
        return placesDao.upsert(*PlacesLocalMapper.toDto(places).toTypedArray())
            .map {  }
    }

    override fun cache(places: List<Place>): Maybe<Any> {
        return Maybe.fromCallable {
            placesDao.cache(*PlacesLocalMapper.toDto(places).toTypedArray())
        }
    }
}