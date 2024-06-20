package com.example.mentalkapp.view.latihan

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mentalkapp.R
import com.example.mentalkapp.data.api.ApiConfigMentest
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.databinding.ActivityLatihan2Binding
import com.example.mentalkapp.view.latihan.latviewmodel.Latihan2ViewModel

class Latihan2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihan2Binding
    private val viewModel: Latihan2ViewModel by viewModels {
        Latihan2ViewModelFactory(ApiConfigMentest.getApiServiceMentest())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihan2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClassify.setOnClickListener {
            val inputText = binding.edInput.text.toString()
            viewModel.sendLatihan(inputText)
        }

        viewModel.response.observe(this) { response ->
            Log.d("Latihan2Activity", "Response received: $response")
            if (response.emotionResult == null) {
                binding.tvResult.text = "No results found"
            } else {
                binding.tvResult.text = "Emotion: ${response.emotionResult}"
            }
        }
    }
    class Latihan2ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Latihan2ViewModel::class.java)) {
                return Latihan2ViewModel(apiService) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}