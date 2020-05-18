package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class HomeworkResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<HomeworkDataResponse>,
    @SerializedName("code")
    val code: Int
)

data class HomeworkDataResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("date")
    val date: String
)