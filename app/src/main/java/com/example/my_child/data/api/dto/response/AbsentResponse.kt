package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class AbsentResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<AbsentDataResponse>,
    @SerializedName("code")
    val code: Int
)

data class AbsentDataResponse(
    @SerializedName("id_parent")
    val childId: Int,
    @SerializedName("id_teacher")
    val teacherId: Int,
    @SerializedName("reason")
    val message: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("date")
    val date: String
)