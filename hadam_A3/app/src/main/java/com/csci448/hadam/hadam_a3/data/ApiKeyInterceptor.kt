package com.csci448.hadam.hadam_a3.data

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("x-rapidapi-key", apiKey)
            .build()
        return chain.proceed(modifiedRequest)
    }
}