package com.gorskisolutions.brewdogchallenge.util

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(private val cacheTtl: Int): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Cache-Control", "public, max-age=$cacheTtl")
            .build()
        return chain.proceed(request)
    }
}
