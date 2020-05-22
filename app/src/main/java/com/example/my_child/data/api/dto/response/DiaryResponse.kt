package com.example.my_child.data.api.dto.response

import com.example.my_child.data.api.dto.data.DiaryData
import com.google.gson.annotations.SerializedName

data class DiaryResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<DiaryData>,
    @SerializedName("code")
    val code: Int
)