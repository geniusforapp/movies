package com.geniusforapp.movies.ui.details.movie

import androidx.lifecycle.ViewModel
import com.geniusforapp.movies.shared.di.scoops.ViewModelKey
import com.geniusforapp.movies.ui.details.about.MovieAboutFragment
import com.geniusforapp.movies.ui.details.company.CompanyFragment
import com.geniusforapp.movies.ui.details.movie.vm.MovieViewModel
import com.geniusforapp.movies.ui.details.similar.SimilarMoviesFragment
import com.geniusforapp.movies.ui.details.similar.SimilarMoviesModule
import com.geniusforapp.movies.ui.details.videos.MovieVideosFragment
import com.geniusforapp.movies.ui.details.videos.MovieVideosModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
@Module(includes = [MovieModule.MovieViewModelModule::class])
abstract class MovieModule {
    @ContributesAndroidInjector
    abstract fun provideCompanyFragment(): CompanyFragment

    @ContributesAndroidInjector
    abstract fun provideAboutFragment(): MovieAboutFragment

    @ContributesAndroidInjector(modules = [SimilarMoviesModule::class])
    abstract fun provideSimilarMoviesFragment(): SimilarMoviesFragment

    @ContributesAndroidInjector(modules = [MovieVideosModule::class])
    abstract fun provideMovieVideosFragment(): MovieVideosFragment


    @Module
    abstract class MovieViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(MovieViewModel::class)
        abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel
    }
}