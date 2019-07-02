package com.geniusforapp.movies.shared.di.modules

import com.geniusforapp.movies.shared.data.repositoreies.ConfigRepositoryImpl
import com.geniusforapp.movies.shared.data.repositoreies.MoviesRepositoryImpl
import com.geniusforapp.movies.shared.data.repositoreies.VideosRepositoryImpl
import com.geniusforapp.movies.shared.domain.repositoreis.ConfigRepository
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import com.geniusforapp.movies.shared.domain.repositoreis.VideosRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun provideConfigRepository(configRepositoryImpl: ConfigRepositoryImpl): ConfigRepository

    @Binds
    abstract fun provideVideosRepository(videosRepositoryImpl: VideosRepositoryImpl): VideosRepository
}