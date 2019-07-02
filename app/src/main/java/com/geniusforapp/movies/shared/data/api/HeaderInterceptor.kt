package com.geniusforapp.movies.shared.data.api

import com.geniusforapp.movies.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        requestBuilder.url(getQueryUrl(request))
        return chain.proceed(requestBuilder.build())
    }

    private fun getQueryUrl(request: Request): HttpUrl {
        return request.url
                .newBuilder()
                .apply { addQueryParameter(ApiConstant.API_KEY, BuildConfig.MOVIE_DB_API_KEY) }
                .build()
    }
}