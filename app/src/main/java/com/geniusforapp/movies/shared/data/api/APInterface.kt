package com.geniusforapp.movies.shared.data.api


import com.geniusforapp.movies.shared.data.model.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APInterface {

    @GET(ApiConstant.GET_MOVIES)
    fun getMovies(@Path(ApiConstant.FILTER_TYPE) filterType: String,
                  @Query(ApiConstant.PAGE) page: Int): Single<MoviesResponse>
}