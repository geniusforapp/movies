package com.geniusforapp.movies.shared.data.repositoreies

import com.geniusforapp.movies.shared.data.api.APInterface
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.shared.domain.repositoreis.VideosRepository
import io.reactivex.Single
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(private val apiInterface: APInterface) : VideosRepository {

    override fun getMovieVideos(movieId: Int): Single<MovieVideos> = apiInterface.getMoviesVideos(movieId)

}