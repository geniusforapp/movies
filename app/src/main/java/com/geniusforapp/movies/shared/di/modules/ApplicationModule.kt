package com.geniusforapp.movies.shared.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniusforapp.movies.application.BaseApplication
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import com.geniusforapp.movies.shared.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class ApplicationModule {


    @Singleton
    @Provides
    fun provideContext(application: BaseApplication): Context {
        return application
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): SchedulerProvider {
        return schedulerProviderImpl
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}