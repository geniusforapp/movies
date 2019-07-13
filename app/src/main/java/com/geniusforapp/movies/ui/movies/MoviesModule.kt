package com.geniusforapp.movies.ui.movies

import androidx.lifecycle.ViewModel
import com.geniusforapp.movies.shared.di.scoops.ViewModelKey
import com.geniusforapp.movies.ui.movies.vm.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/

@Module
abstract class MoviesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(MoviesViewModel: MoviesViewModel): ViewModel
}