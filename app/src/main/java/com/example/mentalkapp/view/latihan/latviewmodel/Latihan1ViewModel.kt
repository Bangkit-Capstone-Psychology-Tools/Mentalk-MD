package com.example.mentalkapp.view.latihan.latviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.data.response.Lat1Response
import kotlinx.coroutines.launch

class Latihan1ViewModel(private val apiService: ApiService) : ViewModel() {
    private val _response = MutableLiveData<Lat1Response>()
    val response: LiveData<Lat1Response> = _response

    fun sendInput(input: String) {
        viewModelScope.launch {
            try {
                val result = apiService.lat1Result(input)
                _response.postValue(result)
            } catch (e: Exception) {
                // Log error or update UI to show error message
                Log.e("Latihan1ViewModel", "Error fetching data", e)
            }
        }
    }
}