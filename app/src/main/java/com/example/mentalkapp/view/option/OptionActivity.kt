package com.example.mentalkapp.view.option

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalkapp.databinding.ActivityOptionBinding
import com.example.mentalkapp.view.latihan.Latihan1Activity
import com.example.mentalkapp.view.latihan.Latihan2Activity
import com.example.mentalkapp.view.latihan.Latihan3Activity
import com.example.mentalkapp.view.latihan.Latihan4Activity

class OptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test1.setOnClickListener{
            val intent= Intent(this, Latihan1Activity::class.java)
            startActivity(intent)
        }
        binding.test2.setOnClickListener{
            val intent= Intent(this, Latihan2Activity::class.java)
            startActivity(intent)
        }
        binding.test3.setOnClickListener{
            val intent= Intent(this, Latihan3Activity::class.java)
            startActivity(intent)
        }
        binding.test4.setOnClickListener{
            val intent= Intent(this, Latihan4Activity::class.java)
            startActivity(intent)
        }
    }
}