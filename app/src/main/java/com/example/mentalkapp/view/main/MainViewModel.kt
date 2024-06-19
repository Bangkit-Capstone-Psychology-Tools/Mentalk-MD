package com.example.mentalkapp.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalkapp.data.api.ApiConfig
import com.example.mentalkapp.data.response.ArticlesItem
import com.example.mentalkapp.data.util.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel: ViewModel() {
    private val _news = MutableLiveData<ResultState<List<ArticlesItem>>>()
    val news = _news
    init {
        getNews()
    }
    private fun getNews() {
        viewModelScope.launch {
            try {
                _news.value = ResultState.Loading
                val response = ApiConfig.getApiService().getNews()
                if (response.status == "ok") {
                    _news.value = ResultState.Success(response.articles)
                }
            } catch (e: HttpException) {
                _news.value = ResultState.Error(e.message())
            }
        }
    }
}