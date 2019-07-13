package com.geniusforapp.movies.shared.di.modules

import androidx.lifecycle.ViewModelProvider
import com.geniusforapp.movies.shared.di.vm.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}