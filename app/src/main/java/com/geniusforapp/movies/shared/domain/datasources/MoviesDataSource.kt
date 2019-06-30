package com.geniusforapp.movies.shared.domain.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.usecases.GetMoviesByTypeUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                           private val getMoviesByTypeUseCase: GetMoviesByTypeUseCase) : ItemKeyedDataSource<Int, MoviesResponse.Result>() {

    private var currentPage: Int = 1
    lateinit var categoryType: String

    lateinit var loaderLiveData: MutableLiveData<Boolean>
    lateinit var errorLiveData: MutableLiveData<Throwable>

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MoviesResponse.Result>) {
        loaderLiveData.postValue(true)
        compositeDisposable.add(getMoviesByTypeUseCase
                .getMovies(categoryType, currentPage)
                .subscribe({
                    loaderLiveData.postValue(false)
                    callback.onResult(it.results)
                }, { errorLiveData.postValue(it) }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {
        loaderLiveData.postValue(true)
        compositeDisposable.add(getMoviesByTypeUseCase
                .getMovies(categoryType, params.key)
                .subscribe({
                    currentPage += 1
                    loaderLiveData.postValue(false)
                    callback.onResult(it.results)
                }, { errorLiveData.postValue(it) }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {

    }

    override fun getKey(item: MoviesResponse.Result): Int = currentPage


}