package com.geniusforapp.movies.shared.domain.repositoreis

import com.geniusforapp.movies.shared.data.model.MoviesResponse

import io.reactivex.Single

interface MoviesRepository {

    fun getMovies(filterType: String, page: Int): Single<MoviesResponse>


}