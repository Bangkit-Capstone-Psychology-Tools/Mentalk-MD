package com.example.mentalkapp.view.latihan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalkapp.R
import com.example.mentalkapp.data.api.ApiConfigMentest
import com.example.mentalkapp.data.api.ApiService
import com.example.mentalkapp.data.response.Lat1Response
import com.example.mentalkapp.databinding.ActivityLatihan1Binding
import com.example.mentalkapp.view.latihan.latviewmodel.Latihan1ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Latihan1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihan1Binding
    private val viewModel: Latihan1ViewModel by viewModels()

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
}