package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class DiaryData(
    @SerializedName("id_parent")
    val childId: Int,
    @SerializedName("id_teacher")
    val teacherId: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("time")
    val time: Long
)