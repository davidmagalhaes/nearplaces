package com.davmag.nearplaces.presentation.di

import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(
        placeRepository: PlaceRepository
    ) = MainViewModel(
        placeRepository
    )
}