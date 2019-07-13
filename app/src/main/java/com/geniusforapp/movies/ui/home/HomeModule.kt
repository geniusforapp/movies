package com.geniusforapp.movies.ui.home

import com.geniusforapp.movies.ui.movies.MoviesFragment
import com.geniusforapp.movies.ui.movies.MoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/

@Module
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [MoviesModule::class])
    abstract fun provideMoviesFragment(): MoviesFragment
}