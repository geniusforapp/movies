package com.geniusforapp.movies.shared.di.modules

import com.geniusforapp.movies.ui.details.movie.MovieActivity
import com.geniusforapp.movies.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    abstract fun provideHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun provideMovieActivity(): MovieActivity
}