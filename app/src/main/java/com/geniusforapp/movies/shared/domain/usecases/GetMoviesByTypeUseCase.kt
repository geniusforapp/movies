package com.geniusforapp.movies.shared.domain.usecases

import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetMoviesByTypeUseCase @Inject constructor(private val moviesRepository: MoviesRepository, private val schedulerProvider: SchedulerProvider) {

    fun getMovies(type: String, page: Int): Single<MoviesResponse> =
            moviesRepository.getMovies(type, page).compose(schedulerProvider.ioToMainSingleScheduler())
}