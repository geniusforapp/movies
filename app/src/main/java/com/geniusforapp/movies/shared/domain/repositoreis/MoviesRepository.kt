package com.geniusforapp.movies.shared.domain.repositoreis

import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.shared.data.model.MoviesResponse

import io.reactivex.Single

interface MoviesRepository {

    fun getMovies(filterType: String, page: Int): Single<MoviesResponse>

    fun getMovieDetails(movieId: Int): Single<MovieDetails>

    fun getSimilarMovies(movieId: Int, page: Int): Single<MoviesResponse>


}