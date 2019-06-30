package com.geniusforapp.movies.shared.domain.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import javax.inject.Inject

class MoviesDataSourceFactory @Inject constructor(private val moviesDataSource: MoviesDataSource) : DataSource.Factory<Int, MoviesResponse.Result>() {
    lateinit var type: String
    lateinit var loaderLiveData: MutableLiveData<Boolean>
    lateinit var errorLiveData: MutableLiveData<Throwable>


    override fun create(): DataSource<Int, MoviesResponse.Result> {
        return moviesDataSource.apply {
            categoryType = type
            loaderLiveData = this@MoviesDataSourceFactory.loaderLiveData
            errorLiveData = this@MoviesDataSourceFactory.errorLiveData
        }
    }
}