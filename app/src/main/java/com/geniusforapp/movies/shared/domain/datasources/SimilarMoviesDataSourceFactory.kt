package com.geniusforapp.movies.shared.domain.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import javax.inject.Inject

class SimilarMoviesDataSourceFactory @Inject constructor(private val similarMoviesDataSource: SimilarMoviesDataSource) : DataSource.Factory<Int, MoviesResponse.Result>() {
    var movieId: Int = 0
    lateinit var loaderLiveData: MutableLiveData<Boolean>
    lateinit var errorLiveData: MutableLiveData<Throwable>
    lateinit var loadMoreLiveData: MutableLiveData<Boolean>


    override fun create(): DataSource<Int, MoviesResponse.Result> {
        return similarMoviesDataSource.apply {
            movieId = this@SimilarMoviesDataSourceFactory.movieId
            loaderLiveData = this@SimilarMoviesDataSourceFactory.loaderLiveData
            errorLiveData = this@SimilarMoviesDataSourceFactory.errorLiveData
            loadMoreLiveData = this@SimilarMoviesDataSourceFactory.loadMoreLiveData
        }
    }
}
