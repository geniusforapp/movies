package com.geniusforapp.movies.shared.data.repositoreies

import com.geniusforapp.movies.shared.data.api.APInterface
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.shared.domain.repositoreis.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiInterface: APInterface) : MoviesRepository {

    override fun getMovies(filterType: String, page: Int): Single<MoviesResponse> {
        return apiInterface.getMovies(filterType, page)
    }
}