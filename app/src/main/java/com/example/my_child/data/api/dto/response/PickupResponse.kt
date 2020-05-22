package com.example.my_child.data.api.dto.response

import com.example.my_child.data.api.dto.data.PickupData
import com.google.gson.annotations.SerializedName

data class PickupResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<PickupData>,
    @SerializedName("code")
    val code: Int
)