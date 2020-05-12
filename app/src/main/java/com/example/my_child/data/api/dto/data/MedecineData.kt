package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class MedicineData(
    @SerializedName("idTeacher")
    val idTeacher: Int,
    @SerializedName("idParent")
    val idParent: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("subText")
    val subtext: String
)
