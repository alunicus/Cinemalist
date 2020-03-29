package com.github.alunicus.cinemalist.core

import com.github.alunicus.cinemalist.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val url = original.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request: Request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
