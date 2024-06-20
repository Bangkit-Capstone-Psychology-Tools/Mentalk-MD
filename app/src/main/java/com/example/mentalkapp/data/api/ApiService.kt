package com.example.mentalkapp.data.api

import com.example.mentalkapp.BuildConfig
import com.example.mentalkapp.data.response.News

import com.example.mentalkapp.data.response.Lat1Response
import com.example.mentalkapp.data.response.Lat2Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "id",
        @Query("category") category: String = "health",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ):News

    @POST("wdyt_yesterday")
    fun postInput(@Body requestBody: Map<String, String>): Call<Lat1Response>

    @POST("text_emotion")
    fun postLatihan(@Body requestBody: Map<String, String>): Call<Lat2Response>
}
