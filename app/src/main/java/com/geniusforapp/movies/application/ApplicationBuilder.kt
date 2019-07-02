package com.geniusforapp.movies.application

import com.geniusforapp.movies.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import javax.inject.Inject

class ApplicationBuilder @Inject constructor(private val context: BaseApplication) {


    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun build() {
        initLogger()
    }
}