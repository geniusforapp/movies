package com.geniusforapp.movies.shared.domain.repositoreis

import com.geniusforapp.movies.shared.data.model.MovieVideos
import io.reactivex.Single

interface VideosRepository {

    fun getMovieVideos(movieId: Int): Single<MovieVideos>

}