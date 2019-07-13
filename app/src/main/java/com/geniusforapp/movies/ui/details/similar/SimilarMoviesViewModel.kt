package com.geniusforapp.movies.ui.details.similar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.datasources.SimilarMoviesDataSourceFactory
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
class SimilarMoviesViewModel @Inject constructor(private val getSimilarMoviesDataSourceFactory: SimilarMoviesDataSourceFactory,
                                                 private val config: PagedList.Config) : ViewModel() {

    private var movies: LiveData<PagedList<MoviesResponse.Result>>? = null


    private var loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private var loadMoreLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getMovies(movieId: Int): LiveData<PagedList<MoviesResponse.Result>> {
        if (movies != null) {
            return movies as LiveData<PagedList<MoviesResponse.Result>>
        }
        this.movies = LivePagedListBuilder(getSimilarMoviesDataSourceFactory.apply {
            this.movieId = movieId
            this.loaderLiveData = this@SimilarMoviesViewModel.loaderLiveData
            this.errorLiveData = this@SimilarMoviesViewModel.errorLiveData
            this.loadMoreLiveData = this@SimilarMoviesViewModel.loadMoreLiveData
        }, config).build()
        return movies as LiveData<PagedList<MoviesResponse.Result>>
    }


    fun getLoaderObserver() = loaderLiveData
    fun getErrorObserver() = errorLiveData
}