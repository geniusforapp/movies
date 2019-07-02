package com.geniusforapp.movies.shared.data.api


import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APInterface {

    @GET(ApiConstant.GET_MOVIES)
    fun getMovies(@Path(ApiConstant.FILTER_TYPE) filterType: String,
                  @Query(ApiConstant.PAGE) page: Int): Single<MoviesResponse>

    @GET(ApiConstant.GET_SIMILAR_MOVIES)
    fun getSimilarMovies(@Path(ApiConstant.MOVIE_ID) movieId: Int, @Query(ApiConstant.PAGE) page: Int): Single<MoviesResponse>

    @GET(ApiConstant.GET_MOVIE_DETAILS)
    fun getMovieDetails(@Path(ApiConstant.MOVIE_ID) movieId: Int): Single<MovieDetails>

    @GET(ApiConstant.GET_MOVIE_VIDEOS)
    fun getMoviesVideos(@Path(ApiConstant.MOVIE_ID) movieId: Int): Single<MovieVideos>
}