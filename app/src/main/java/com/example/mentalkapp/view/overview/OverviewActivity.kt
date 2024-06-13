package com.example.mentalkapp.view.overview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mentalkapp.databinding.ActivityOverviewBinding
import com.example.mentalkapp.view.option.OptionActivity

class OverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener{
            val intent= Intent(this, OptionActivity::class.java)
            startActivity(intent)
        }
    }
}