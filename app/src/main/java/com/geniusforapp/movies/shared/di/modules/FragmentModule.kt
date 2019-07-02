package com.geniusforapp.movies.shared.di.modules

import com.geniusforapp.movies.ui.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {


    @ContributesAndroidInjector
    abstract fun provideMoviesFragment(): MoviesFragment

}