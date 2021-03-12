package com.davmag.nearplaces.data.source.remote.mapper

import com.davmag.nearplaces.data.common.EntityMapper
import com.davmag.nearplaces.data.source.remote.dto.PlaceObject
import com.davmag.nearplaces.domain.model.Place

object PlaceRemoteMapper : EntityMapper<Place, PlaceObject> {
    override val toDtoMapper: (Place) -> PlaceObject
        get() = TODO("Not yet implemented")
    override val toEntityMapper: (PlaceObject) -> Place = {
        Place(
            id = it.id,
            name = it.name
        )
    }
}