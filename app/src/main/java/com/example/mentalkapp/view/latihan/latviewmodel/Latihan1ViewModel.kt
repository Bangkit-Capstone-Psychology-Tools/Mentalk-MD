package com.example.mentalkapp.view.latihan.latviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.data.response.Lat1Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Latihan1ViewModel(private val apiService: ApiService) : ViewModel() {
    private val _response = MutableLiveData<Lat1Response>()
    val response: LiveData<Lat1Response> = _response

    fun sendInput(answer: String) {
        val requestBody = mapOf("answer" to answer)
        apiService.postInput(requestBody).enqueue(object : Callback<Lat1Response> {
            override fun onResponse(call: Call<Lat1Response>, response: Response<Lat1Response>) {
                if (response.isSuccessful) {
                    _response.postValue(response.body())
                    Log.d("Latihan1ViewModel", "API Success: ${response.body()}")
                } else {
                    Log.e("Latihan1ViewModel", "API Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Lat1Response>, t: Throwable) {
                Log.e("Latihan1ViewModel", "API Failure: ${t.message}")
            }
        })
    }
}