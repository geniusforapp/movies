package com.geniusforapp.movies.shared.domain.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.usecases.GetSimilarMoviesByIdUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SimilarMoviesDataSource @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                                  private val getSimilarMoviesByIdUseCase: GetSimilarMoviesByIdUseCase) : ItemKeyedDataSource<Int, MoviesResponse.Result>() {

    private var currentPage: Int = 1
    var movieId: Int = 0

    lateinit var loaderLiveData: MutableLiveData<Boolean>
    lateinit var errorLiveData: MutableLiveData<Throwable>
    lateinit var loadMoreLiveData: MutableLiveData<Boolean>

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MoviesResponse.Result>) {
        loaderLiveData.postValue(true)
        compositeDisposable.add(getSimilarMoviesByIdUseCase
                .getSimilarMovies(movieId, currentPage)
                .subscribe({
                    loaderLiveData.postValue(false)
                    callback.onResult(it.results)
                }, { errorLiveData.postValue(it) }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {
        loadMoreLiveData.postValue(true)
        compositeDisposable.add(getSimilarMoviesByIdUseCase
                .getSimilarMovies(movieId, params.key)
                .subscribe({
                    currentPage += 1
                    loadMoreLiveData.postValue(false)
                    callback.onResult(it.results)
                }, { errorLiveData.postValue(it) }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {

    }


    override fun getKey(item: MoviesResponse.Result): Int = currentPage


}