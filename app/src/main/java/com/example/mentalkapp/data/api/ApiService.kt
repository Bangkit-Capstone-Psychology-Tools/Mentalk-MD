package com.example.mentalkapp.data.api

import com.example.mentalkapp.BuildConfig
import com.example.mentalkapp.data.response.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "id",
        @Query("category") category: String = "health",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ):News
}