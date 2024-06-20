package com.example.mentalkapp.data.model

data class ResultResponse(
    val emotion_result: List<String>,
    val topic_result: List<String>
)