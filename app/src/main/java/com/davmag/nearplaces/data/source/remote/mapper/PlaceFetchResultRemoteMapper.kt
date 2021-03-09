package com.davmag.nearplaces.data.source.remote.mapper

import com.davmag.nearplaces.data.common.EntityMapper
import com.davmag.nearplaces.data.source.remote.dto.PlaceFetchResult
import com.davmag.nearplaces.domain.aggregate.PlaceSearchResult

object PlaceFetchResultRemoteMapper : EntityMapper<PlaceSearchResult, PlaceFetchResult> {
    override val toDtoMapper: (PlaceSearchResult) -> PlaceFetchResult
        get() = TODO("Not yet implemented")
    override val toEntityMapper: (PlaceFetchResult) -> PlaceSearchResult = {
        PlaceSearchResult(
            nextPageToken = it.nextPageToken,
            places = PlaceRemoteMapper.toEntity(it.results)
        )
    }
}