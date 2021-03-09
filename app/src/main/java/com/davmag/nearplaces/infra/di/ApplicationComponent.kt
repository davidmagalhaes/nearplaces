package com.davmag.nearplaces.infra.di

import android.app.Application
import com.davmag.nearplaces.domain.repository.PlaceRepository
import com.davmag.nearplaces.infra.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    PersistenceModule::class,
    NetworkModule::class,
    DatasourceModule::class,
    RepositoryModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }

    fun applicaton() : Application

    fun inject(application : App)

    fun placeRepository() : PlaceRepository
}