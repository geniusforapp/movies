package com.geniusforapp.movies.shared.domain.usecases

import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepository,
                                                 private val schedulerProvider: SchedulerProvider) {


    fun getMovieDetails(movieId: Int): Single<MovieDetails> = moviesRepository.getMovieDetails(movieId)
            .compose(schedulerProvider.ioToMainSingleScheduler())
}