package com.example.mentalkapp.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfigMentest {
    companion object {
        fun getApiServiceMentest(): ApiService {
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)  // Increase connect timeout
                .readTimeout(30, TimeUnit.SECONDS)     // Increase read timeout
                .writeTimeout(30, TimeUnit.SECONDS)    // Increase write timeout
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("-")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}