package com.example.mentalkapp.view.option

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalkapp.databinding.ActivityOptionBinding
import com.example.mentalkapp.view.latihan.Latihan1Activity
import com.example.mentalkapp.view.latihan.Latihan2Activity

class OptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test1.setOnClickListener{
            try {
                val intent = Intent(this, Latihan1Activity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("OptionActivity", "Error starting Latihan1Activity", e)
            }
        }
        binding.test2.setOnClickListener{
            val intent= Intent(this, Latihan2Activity::class.java)
            startActivity(intent)
        }
    }
}