package com.example.mentalkapp.data.response
import com.google.gson.annotations.SerializedName

data class Lat2Response(
	@SerializedName("emotion_result")
	val emotionResult: String?
)

