package com.example.mentalkapp.data.model

data class QuestionModel(
    val token: String,
    val id: String,
    val questionText: String,
    val options: List<String>
)