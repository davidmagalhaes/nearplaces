package com.davmag.nearplaces.presentation.mapper

import com.davmag.nearplaces.domain.model.Place
import com.davmag.nearplaces.presentation.common.ExceptionWrapper
import com.davmag.nearplaces.presentation.common.PresentationMapper
import com.davmag.nearplaces.presentation.model.PlacePresentation

object PlacePresentationMapper : PresentationMapper<Place, PlacePresentation>() {
    override val contentMapper: (List<Place>) -> List<PlacePresentation> = { places ->
        places.map {
            PlacePresentation(
                id = it.id,
                name = it.name
            )
        }
    }

    override val errorMapper: (Throwable) -> ExceptionWrapper = {
        ExceptionWrapper(
            exception = it
        )
    }
}