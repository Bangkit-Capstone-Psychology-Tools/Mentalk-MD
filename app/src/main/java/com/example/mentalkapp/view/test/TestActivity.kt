package com.example.mentalkapp.view.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mentalkapp.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(this)


    }
}

private fun Button.setOnClickListener(testActivity: TestActivity) {
}


