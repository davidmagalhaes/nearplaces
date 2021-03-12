package com.davmag.nearplaces.presentation.di

import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.domain.usecase.FetchPlacesUseCase
import com.davmag.nearplaces.domain.usecase.GetPlacesUseCase
import com.davmag.nearplaces.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(
        getPlacesUseCase: GetPlacesUseCase,
        fetchPlacesUseCase: FetchPlacesUseCase
    ) = MainViewModel(
        getPlacesUseCase,
        fetchPlacesUseCase
    )
}