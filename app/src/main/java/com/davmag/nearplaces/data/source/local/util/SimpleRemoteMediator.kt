package com.davmag.nearplaces.data.source.local.util

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

@ExperimentalPagingApi
class SimpleRemoteMediator<T : Any, Q : Any>(
    private val loadItems : suspend () -> List<Q>?
) : RemoteMediator<T, Q>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<T, Q>
    ): MediatorResult {
        if(loadType == LoadType.PREPEND){
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        val items = loadItems()
        return MediatorResult.Success(
            endOfPaginationReached = items.orEmpty().isEmpty()
        )
    }
}