package com.davmag.nearplaces.infra.di

import com.davmag.nearplaces.data.source.local.contract.LocationLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.PlaceLocalDatasource
import com.davmag.nearplaces.data.source.local.contract.UserContextLocalDatasource
import com.davmag.nearplaces.data.source.local.dao.PlacesDao
import com.davmag.nearplaces.data.source.local.impl.LocationLocalDatasouceImpl
import com.davmag.nearplaces.data.source.local.impl.PlaceLocalDatasourceImpl
import com.davmag.nearplaces.data.source.local.impl.UserContextLocalDatasourceImpl
import com.davmag.nearplaces.data.source.remote.api.PlacesApi
import com.davmag.nearplaces.data.source.remote.contract.PlaceRemoteDatasource
import com.davmag.nearplaces.data.source.remote.impl.PlaceRemoteDatasourceImpl
import com.google.gson.Gson
import com.orhanobut.hawk.HawkFacade
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatasourceModule {
    @Singleton
    @Provides
    fun providePlacesLocalDatasource(
        placesDao: PlacesDao
    ) : PlaceLocalDatasource =
        PlaceLocalDatasourceImpl(placesDao)

    @Singleton
    @Provides
    fun providePlaceRemoteDatasource(
        placesApi: PlacesApi
    ) : PlaceRemoteDatasource =
        PlaceRemoteDatasourceImpl(placesApi)


    @Singleton
    @Provides
    fun provideLocationLocalDatasource() : LocationLocalDatasource =
        LocationLocalDatasouceImpl()

    @Singleton
    @Provides
    fun provideUserContextLocalDatasource(
        gson : Gson,
        hawk: HawkFacade
    ) : UserContextLocalDatasource =
        UserContextLocalDatasourceImpl(gson, hawk)
}