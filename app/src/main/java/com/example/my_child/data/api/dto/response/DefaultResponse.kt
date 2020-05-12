package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: Boolean,
    @SerializedName("code")
    val code: Int
)