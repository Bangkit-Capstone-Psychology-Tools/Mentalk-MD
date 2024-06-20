package com.example.mentalkapp.view.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalkapp.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tittle = intent.getStringExtra("NEWS_TITTLE")
        val publishedat = intent.getStringExtra("NEWS_PUBLISHED")

        binding.tittle.text = tittle
        binding.published.text = publishedat
    }
}