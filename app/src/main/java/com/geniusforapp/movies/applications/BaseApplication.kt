package com.geniusforapp.movies.applications


import com.geniusforapp.movies.BuildConfig
import com.geniusforapp.movies.shared.di.components.DaggerApplicationComponent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by anajar on 5/14/17.
 */
class BaseApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<BaseApplication> = DaggerApplicationComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        logger()
    }


    private fun logger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}
