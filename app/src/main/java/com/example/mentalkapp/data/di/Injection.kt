package com.example.mentalkapp.data.di

import android.content.Context
import com.example.mentalkapp.data.api.ApiConfig
import com.example.mentalkapp.data.userdata.UserPreference
import com.example.mentalkapp.data.userdata.UserRepository
import com.example.mentalkapp.data.userdata.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {   fun provideRepository(context: Context): UserRepository {
    val pref = UserPreference.getInstance(context.dataStore)
    val user = runBlocking { pref.getSession().first() }
    val apiService = ApiConfig.getApiService(user.token)
    return UserRepository.getInstance(apiService, pref)
}
}