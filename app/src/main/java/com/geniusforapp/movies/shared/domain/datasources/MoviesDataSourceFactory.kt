package com.geniusforapp.movies.shared.domain.datasources

import androidx.paging.DataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSourceFactory @Inject constructor(private val moviesDataSource: MoviesDataSource) : DataSource.Factory<Int, MoviesResponse.Result>() {
    lateinit var type: String

    override fun create(): DataSource<Int, MoviesResponse.Result> {
        moviesDataSource.categoryType = type
        return moviesDataSource
    }
}