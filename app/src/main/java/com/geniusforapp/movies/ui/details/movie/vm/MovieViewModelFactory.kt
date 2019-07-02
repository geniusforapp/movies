package com.geniusforapp.movies.ui.details.movie.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geniusforapp.movies.shared.domain.datasources.SimilarMoviesDataSourceFactory
import com.geniusforapp.movies.shared.domain.usecases.GetMovieDetailsUseCase
import com.geniusforapp.movies.shared.domain.usecases.GetMovieVideosByIdUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                                private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                                private val getMovieVideosByIdUseCase: GetMovieVideosByIdUseCase,
                                                private var similarMoviesDataSourceFactory: SimilarMoviesDataSourceFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieViewModel(
            getMovieDetailsUseCase,
            getMovieVideosByIdUseCase,
            similarMoviesDataSourceFactory,
            compositeDisposable) as T
}