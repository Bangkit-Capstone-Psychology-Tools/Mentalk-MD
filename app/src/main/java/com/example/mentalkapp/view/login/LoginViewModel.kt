package com.example.mentalkapp.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentalkapp.data.response.LoginResponse
import com.example.mentalkapp.data.userdata.UserModel
import com.example.mentalkapp.data.userdata.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var loginResult : MutableLiveData<LoginResponse> = repository.login
    var isLoading: LiveData<Boolean> = repository.isLoading


    fun executelogin(email: String, password: String) {
        return repository.executelogin(email, password)
    }

    suspend fun saveUserSession(user: UserModel) {
        repository.saveUserSession(user)
    }
}