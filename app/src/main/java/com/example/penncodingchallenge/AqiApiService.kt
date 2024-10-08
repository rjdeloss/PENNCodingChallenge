package com.example.penncodingchallenge

import com.example.penncodingchallenge.models.AqiData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AqiApi {
    @GET("/feed/{city}/")
    suspend fun getAqiWithCity(@Path("city") city: String, @Query("token") token: String): Response<AqiData>

    @GET("/feed/here/")
    suspend fun getAQIWithIPAddress(): Response<AqiData>
    @GET("/feed/geo:")
    suspend fun getNearestStation(@Query("key") token: String): Response<AqiData>

    @GET("/search")
    suspend fun searchLocation(@Query("key") token: String, keyword: String): Response<AqiData>
}