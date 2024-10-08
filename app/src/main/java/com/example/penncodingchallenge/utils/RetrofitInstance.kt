package com.example.penncodingchallenge.utils

import com.example.penncodingchallenge.AqiApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.waqi.info"

    val api : AqiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val urlWithApiKey = chain.request().url.newBuilder()
                            .addQueryParameter("token", "dae702f98ec0f3a68a36b6b49b327b4c781727e9")
                            .build()
                        val request = chain.request().newBuilder()
                            .url(urlWithApiKey)
                            .build()
                        chain.proceed(request)
                    }
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .build()
            .create(AqiApiService::class.java)
    }
}