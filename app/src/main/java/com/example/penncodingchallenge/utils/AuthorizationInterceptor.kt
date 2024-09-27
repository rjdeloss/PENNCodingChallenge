package com.example.penncodingchallenge.utils

import okhttp3.Response
import okhttp3.Interceptor as Interceptor

class AuthorizationInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}