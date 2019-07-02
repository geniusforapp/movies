package com.geniusforapp.movies.shared.domain.usecases

import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetSimilarMoviesByIdUseCase @Inject constructor(private val moviesRepository: MoviesRepository, private val schedulerProvider: SchedulerProvider) {

    fun getSimilarMovies(movieId: Int, page: Int): Single<MoviesResponse> =
            moviesRepository.getSimilarMovies(movieId, page).compose(schedulerProvider.ioToMainSingleScheduler())
}