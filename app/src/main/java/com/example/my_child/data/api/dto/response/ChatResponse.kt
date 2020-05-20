package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<ChatDataResponse>,
    @SerializedName("code")
    val code: Int
)

data class ChatDataResponse(
    @SerializedName("idParent")
    val childId: Int,
    @SerializedName("idTeacher")
    val teacherId: Int,
    @SerializedName("sender")
    val sender: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("message")
    val message: String
)