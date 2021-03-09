package com.davmag.nearplaces.infra.di

import com.davmag.nearplaces.BuildConfig
import com.davmag.nearplaces.data.source.remote.api.PlacesApi
import com.davmag.nearplaces.data.repository.scheduler.AppSchedulers
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectionPool(
                ConnectionPool(
                    10,
                    BuildConfig.NETWORK_TIMEOUT,
                    TimeUnit.SECONDS
                )
            )
            .readTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .apply {
                if (BuildConfig.DEBUG) addNetworkInterceptor(StethoInterceptor())
            }
            .build()
    }

    @Named("GoogleRetrofit")
    @Singleton
    @Provides
    fun provideGoogleRetrofit(
        gson : Gson,
        appSchedulers: AppSchedulers
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GOOGLE_MAPS_ROOT_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(appSchedulers.network())
            )
            .build()
    }

    @Singleton
    @Provides
    fun providePlacesApi(
        @Named("GoogleRetrofit") googleRetrofit : Retrofit
    ) : PlacesApi = googleRetrofit.create(PlacesApi::class.java)

}