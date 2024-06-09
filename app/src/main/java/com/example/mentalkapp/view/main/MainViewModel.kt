package com.example.mentalkapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mentalkapp.data.userdata.UserModel
import com.example.mentalkapp.data.userdata.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _logoutComplete = MutableLiveData<Boolean>()
    val logoutComplete: LiveData<Boolean>
        get() = _logoutComplete

    var isLoading: LiveData<Boolean> = repository.isLoading

    fun getUserSession(): LiveData<UserModel> {
        return repository.getUserSession().asLiveData()
    }
    fun clearUserSession() {
        viewModelScope.launch {
            repository.clearUserSession()
            _logoutComplete.postValue(true)
        }
    }


}