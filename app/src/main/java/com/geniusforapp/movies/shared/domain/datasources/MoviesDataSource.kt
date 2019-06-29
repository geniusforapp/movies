package com.geniusforapp.movies.shared.domain.datasources

import androidx.paging.ItemKeyedDataSource
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.usecases.GetMoviesByTypeUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSource @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                           private val getMoviesByTypeUseCase: GetMoviesByTypeUseCase) : ItemKeyedDataSource<Int, MoviesResponse.Result>() {

    private var currentPage: Int = 1
    lateinit var categoryType: String

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MoviesResponse.Result>) {
        compositeDisposable.add(getMoviesByTypeUseCase
                .getMovies(categoryType, currentPage)
                .subscribe({ callback.onResult(it.results) }, {}))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {
        compositeDisposable.add(getMoviesByTypeUseCase
                .getMovies(categoryType, params.key)
                .subscribe({
                    currentPage += 1
                    callback.onResult(it.results)
                }, {}))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MoviesResponse.Result>) {

    }

    override fun getKey(item: MoviesResponse.Result): Int = currentPage


}