package com.example.mentalkapp.view.register

import androidx.lifecycle.ViewModel
import com.example.mentalkapp.data.response.ErrorResponse
import com.example.mentalkapp.data.userdata.UserRepository

class RegisterViewModel (private var repository: UserRepository) : ViewModel() {

    suspend fun register(name: String, email: String, password: String): ErrorResponse {
        return repository.register(name, email, password)
    }
}