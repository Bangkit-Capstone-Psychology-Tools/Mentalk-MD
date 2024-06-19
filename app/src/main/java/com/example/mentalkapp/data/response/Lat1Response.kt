package com.example.mentalkapp.data.response

import com.google.gson.annotations.SerializedName

data class Lat1Response(

	@field:SerializedName("topic_result")
	val topicResult: List<String?>? = null,

	@field:SerializedName("emotion_result")
	val emotionResult: List<String?>? = null
)
