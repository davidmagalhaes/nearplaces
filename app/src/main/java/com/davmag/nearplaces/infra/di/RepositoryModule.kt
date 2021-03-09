package com.davmag.nearplaces.infra.di

import com.davmag.nearplaces.data.source.local.contract.LocationLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.PlaceLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.UserContextLocalDatasource
import com.davmag.nearplaces.data.source.remote.contract.PlaceRemoteDatasource
import com.davmag.nearplaces.data.source.repository.PlaceRepositoryImpl
import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.domain.repository.scheduler.AppSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providePlacesRepository(
        appSchedulers: AppSchedulers,
        userContextLocalDatasource: UserContextLocalDatasource,
        locationLocalDatasource: LocationLocalDatasource,
        placeLocalDatasource: PlaceLocalDatasource,
        placeRemoteDatasource: PlaceRemoteDatasource
    ) : PlaceRepository =
        PlaceRepositoryImpl(
            appSchedulers,
            userContextLocalDatasource,
            locationLocalDatasource,
            placeLocalDatasource,
            placeRemoteDatasource
        )
}