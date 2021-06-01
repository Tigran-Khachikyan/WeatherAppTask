package com.example.weatherapptask.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response


class BasicAuthInterceptor : Interceptor {

    companion object {
        private const val AUTH_PARAM = "appid"
        private const val API_KEY = "c0efeed947367d9cbf9e175ab3eac99b"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
                .newBuilder()
                .addQueryParameter(AUTH_PARAM, API_KEY)
                .build()
        return chain.proceed(chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}
