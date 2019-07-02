package com.geniusforapp.movies.shared.data.repositoreies

import android.content.SharedPreferences
import com.geniusforapp.movies.shared.domain.repositoreis.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : ConfigRepository {


    fun getConfig() {

        val configString = sharedPreferences.getString("", "")
        configString?.let { }


        return
    }
}