package com.geniusforapp.movies.shared.domain.usecases

import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.shared.domain.repositoreis.VideosRepository
import com.geniusforapp.movies.shared.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class GetMovieVideosByIdUseCase @Inject constructor(private val videosRepository: VideosRepository,
                                                    private val schedulerProvider: SchedulerProvider) {

    fun getMovieVideos(movieId: Int): Single<MovieVideos> = videosRepository
            .getMovieVideos(movieId)
            .compose(schedulerProvider.ioToMainSingleScheduler())
}