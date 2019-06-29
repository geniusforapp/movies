package com.geniusforapp.movies.shared.di.components

import com.geniusforapp.movies.applications.BaseApplication
import com.geniusforapp.movies.shared.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    FragmentModule::class,
    RepositoriesModule::class
])
interface ApplicationComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()
}