package com.geniusforapp.movies.shared.di.modules

import com.geniusforapp.movies.ui.details.movie.MovieActivity
import com.geniusforapp.movies.ui.details.movie.MovieModule
import com.geniusforapp.movies.ui.home.HomeActivity
import com.geniusforapp.movies.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {


    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun provideHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun provideMovieActivity(): MovieActivity
}