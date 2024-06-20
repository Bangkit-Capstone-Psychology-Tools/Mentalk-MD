package com.example.mentalkapp.view.latihan.latviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.data.response.Lat2Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Latihan2ViewModel(private val apiService: ApiService) : ViewModel() {
    private val _response = MutableLiveData<Lat2Response>()
    val response: LiveData<Lat2Response> = _response

    fun sendLatihan(answer: String) {
        val requestBody = mapOf("answer" to answer)
        apiService.postLatihan(requestBody).enqueue(object : Callback<Lat2Response> {
            override fun onResponse(call: Call<Lat2Response>, response: Response<Lat2Response>) {
                if (response.isSuccessful) {
                    _response.postValue(response.body())
                } else {
                    Log.e("Latihan2ViewModel", "API Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Lat2Response>, t: Throwable) {
                Log.e("Latihan2ViewModel", "API Failure: ${t.message}")
            }
        })
    }
}