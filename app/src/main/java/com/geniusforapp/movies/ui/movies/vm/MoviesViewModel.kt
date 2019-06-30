package com.geniusforapp.movies.ui.movies.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.datasources.MoviesDataSourceFactory
import javax.inject.Inject
import javax.inject.Singleton

class MoviesViewModel @Inject constructor(private val moviesDataSourceFactory: MoviesDataSourceFactory) :
        ViewModel() {


    private var movies: LiveData<PagedList<MoviesResponse.Result>>? = null


    private var loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    private var config: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(true)
            .build()


    fun getMovies(type: String): LiveData<PagedList<MoviesResponse.Result>> {
        if (movies != null) {
            return movies as LiveData<PagedList<MoviesResponse.Result>>
        }

        this.movies = LivePagedListBuilder(moviesDataSourceFactory.apply {
            this.type = type
            this.loaderLiveData = this@MoviesViewModel.loaderLiveData
            this.errorLiveData = this@MoviesViewModel.errorLiveData
        }, config).build()
        return movies as LiveData<PagedList<MoviesResponse.Result>>
    }


    fun getLoaderObserver() = loaderLiveData
    fun getErrorObserver() = errorLiveData

}