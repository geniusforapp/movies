package com.geniusforapp.movies.shared.di.modules

import com.geniusforapp.movies.shared.data.repositoreies.MoviesRepositoryImpl
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}