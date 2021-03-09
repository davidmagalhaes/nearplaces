package com.davmag.nearplaces.infra.di

import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.domain.usecase.FetchPlaceUseCaseImpl
import com.davmag.nearplaces.domain.usecase.FetchPlacesUseCase
import com.davmag.nearplaces.domain.usecase.GetPlacesUseCase
import com.davmag.nearplaces.domain.usecase.GetPlacesUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseRepository {
    @Provides
    @Singleton
    fun provideGetPlacesUseCase(
        placeRepository: PlaceRepository
    ) : GetPlacesUseCase =
        GetPlacesUseCaseImpl(
            placeRepository
        )

    @Provides
    @Singleton
    fun provideFetchPlacesUseCase(
        placeRepository: PlaceRepository
    ) : FetchPlacesUseCase =
        FetchPlaceUseCaseImpl(
            placeRepository
        )
}