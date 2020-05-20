package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class ChatData(
    @SerializedName("id_teacher")
    val idTeacher: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("date")
    val date: String
)