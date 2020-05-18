package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class ClassListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<ParentDataResponse>,
    @SerializedName("code")
    val code: Int
)