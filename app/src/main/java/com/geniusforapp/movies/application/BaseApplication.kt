package com.geniusforapp.movies.application


import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class BaseApplication : DaggerApplication() {


    @Inject
    lateinit var applicationBuilder: ApplicationBuilder

    override fun applicationInjector(): AndroidInjector<BaseApplication> =
            DaggerApplicationComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        applicationBuilder.build()
    }

}
