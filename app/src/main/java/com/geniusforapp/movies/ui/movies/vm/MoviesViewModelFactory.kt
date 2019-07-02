package com.geniusforapp.movies.ui.movies.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geniusforapp.movies.shared.domain.datasources.MoviesDataSourceFactory
import com.geniusforapp.movies.shared.domain.usecases.GetMoviesByTypeUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesViewModelFactory @Inject constructor(private val moviesDataSourceFactory: MoviesDataSourceFactory) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesDataSourceFactory) as T
    }
}