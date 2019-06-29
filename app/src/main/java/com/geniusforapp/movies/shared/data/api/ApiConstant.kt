package com.geniusforapp.movies.shared.data.api

object ApiConstant {
    const val PAGE = "page"

    const val API_KEY = "api_key"

    const val FILTER_TYPE = "type"

    private const val MOVIE = "movie"

    const val GET_MOVIES = "$MOVIE/{$FILTER_TYPE}"
}