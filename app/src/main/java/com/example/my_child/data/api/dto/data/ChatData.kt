package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class ChatData(
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