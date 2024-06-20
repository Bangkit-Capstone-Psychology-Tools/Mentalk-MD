package com.example.mentalkapp.data.api

import com.example.mentalkapp.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
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
                .baseUrl("https://mentalk-image-ytfxja5liq-et.a.run.app/model/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}