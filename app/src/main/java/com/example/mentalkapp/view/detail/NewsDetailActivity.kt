package com.example.mentalkapp.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mentalkapp.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tittle = intent.getStringExtra("NEWS_TITTLE")
        val detailDesc = intent.getStringExtra("NEWS_DESC")
        val urlToImage = intent.getStringExtra("NEWS_URL_TO_IMAGE")

        binding.tittle.text = tittle
        binding.detailDesc.text = detailDesc
        Glide.with(this)
            .load(urlToImage)
            .into(binding.detailImage)
    }
}