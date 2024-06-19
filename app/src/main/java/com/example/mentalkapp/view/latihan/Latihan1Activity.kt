package com.example.mentalkapp.view.latihan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mentalkapp.data.api.ApiConfigMentest
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.databinding.ActivityLatihan1Binding
import com.example.mentalkapp.view.latihan.latviewmodel.Latihan1ViewModel


class Latihan1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihan1Binding
    private val viewModel: Latihan1ViewModel by viewModels {
        Latihan1ViewModelFactory(ApiConfigMentest.getApiServiceMentest())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihan1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClassify.setOnClickListener {
            val inputText = binding.edInput.text.toString()
            viewModel.sendInput(inputText)
        }

        viewModel.response.observe(this) { response ->
            binding.tvResult.text = "Emotion: ${response.emotionResult?.joinToString()} \nTopic: ${response.topicResult?.joinToString()}"
        }
    }
    class Latihan1ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Latihan1ViewModel::class.java)) {
                return Latihan1ViewModel(apiService) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}