package com.davmag.nearplaces.infra.di

import android.app.Application
import androidx.room.Room
import com.davmag.nearplaces.data.source.local.LocalDatabase
import com.orhanobut.hawk.DefaultHawkFacade
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkFacade
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideHawk(application: Application) : HawkFacade =
        DefaultHawkFacade(
            Hawk.init(application)
                .setLogInterceptor {
                    Timber.i(it)
                }
        )

    @Singleton
    @Provides
    fun provideDatabase(
        application: Application
    ) : LocalDatabase {
        return Room.databaseBuilder(
                application,
                LocalDatabase::class.java,
                "nearplaces.db"
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun providePlacesDao(
        localDatabase: LocalDatabase
    ) = localDatabase.getPlacesDao()
}