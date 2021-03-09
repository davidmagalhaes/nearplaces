package com.davmag.nearplaces.data.source.local.mapper

import com.davmag.nearplaces.data.common.EntityMapper
import com.davmag.nearplaces.data.source.local.dto.PlaceDb
import com.davmag.nearplaces.domain.model.Place

object PlacesLocalMapper : EntityMapper<Place, PlaceDb> {
    override val toEntityMapper: (PlaceDb) -> Place = {
        Place(id = it.id, name = it.name)
    }

    override val toDtoMapper: (Place) -> PlaceDb = {
        PlaceDb(id = it.id, name = it.name)
    }
}