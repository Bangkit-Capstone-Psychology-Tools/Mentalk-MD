package com.example.mentalkapp.data.api

import com.example.mentalkapp.BuildConfig
import com.example.mentalkapp.data.response.News

import com.example.mentalkapp.data.response.Lat1Response
import com.example.mentalkapp.data.response.Lat2Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("everything-")
    suspend fun getNews(
        @Query("anything") country: String = "id",
        @Query("anything") category: String = "health",
        @Query("anything") apiKey: String = "-"
    ):News

    @POST("everything-")
    fun postInput(@Body requestBody: Map<String, String>): Call<Lat1Response>

    @POST("everything-")
    fun postLatihan(@Body requestBody: Map<String, String>): Call<Lat2Response>
}
