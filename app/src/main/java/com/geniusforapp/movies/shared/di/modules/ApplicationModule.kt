package com.geniusforapp.movies.shared.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniusforapp.movies.applications.BaseApplication
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import com.geniusforapp.movies.shared.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class ApplicationModule {


    @Singleton
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

}