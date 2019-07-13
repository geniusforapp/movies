package com.geniusforapp.movies.ui.details.videos

import androidx.lifecycle.ViewModel
import com.geniusforapp.movies.shared.di.scoops.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/

@Module
abstract class MovieVideosModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieVideosViewModel::class)
    abstract fun bindMovieVideosViewModel(movieVideosViewModel: MovieVideosViewModel): ViewModel
}