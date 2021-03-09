package com.davmag.nearplaces.infra.di

import com.davmag.nearplaces.infra.util.AppSchedulersImpl
import com.davmag.nearplaces.domain.repository.scheduler.AppSchedulers
import com.davmag.nearplaces.infra.util.GsonDateTimeTypeAdapter
import com.davmag.nearplaces.infra.util.GsonSimpleDateTypeAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import javax.inject.Singleton

@Module
class BaseModule {
    @Singleton
    @Provides
    fun provideAppSchedulers() : AppSchedulers = AppSchedulersImpl

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, GsonDateTimeTypeAdapter)
        .registerTypeAdapter(LocalDate::class.java, GsonSimpleDateTypeAdapter)
        .create()
}