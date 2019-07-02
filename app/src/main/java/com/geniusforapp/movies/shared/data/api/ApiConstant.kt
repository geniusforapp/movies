package com.geniusforapp.movies.shared.data.api

object ApiConstant {
    const val PAGE = "page"

    const val API_KEY = "api_key"

    const val FILTER_TYPE = "type"

    private const val MOVIE = "movie"

    const val MOVIE_ID = "movieId"

    const val GET_MOVIES = "$MOVIE/{$FILTER_TYPE}"

    const val GET_MOVIE_DETAILS = "$MOVIE/{$MOVIE_ID}"

    const val GET_MOVIE_VIDEOS = "$MOVIE/{$MOVIE_ID}/videos"

    const val GET_SIMILAR_MOVIES = "$MOVIE/{$MOVIE_ID}/similar"
}