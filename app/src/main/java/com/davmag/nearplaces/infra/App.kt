package com.davmag.nearplaces.infra

import android.app.Application
import com.davmag.nearplaces.BuildConfig
import com.davmag.nearplaces.infra.di.ApplicationComponent
import com.davmag.nearplaces.infra.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.hawk.Hawk
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {
    companion object  {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        AndroidThreeTen.init(this)
        Hawk.init(this)

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }

        RxJavaPlugins.setErrorHandler {
            Timber.e(it, "RxJava error handled on Global Handler!")
        }

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationBind(this)
            .build()

        super.onCreate()
    }
}