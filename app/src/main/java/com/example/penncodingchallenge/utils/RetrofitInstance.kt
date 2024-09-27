package com.example.penncodingchallenge.utils

import com.example.penncodingchallenge.AqiApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient as OkHttpClient

object RetrofitInstance {
    private const val BASE_URL = "https://api.waqi.info"

    val api : AqiApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AqiApiInterface::class.java)
    }
}