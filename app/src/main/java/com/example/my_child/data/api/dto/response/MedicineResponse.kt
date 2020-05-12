package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class MedicineResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<MedicineDataResponse>,
    @SerializedName("code")
    val code: Int
)

data class MedicineDataResponse(
    @SerializedName("idTeacher")
    val idTeacher: Int,
    @SerializedName("idParent")
    val idParent: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("subText")
    val subText: String
)