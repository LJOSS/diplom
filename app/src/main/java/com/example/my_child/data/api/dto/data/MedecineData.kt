package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class MedicineData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("idTeacher")
    val idTeacher: Int,
    @SerializedName("idParent")
    val idParent: Int,
    @SerializedName("date")
    val date: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("frequency")
    val frequency: String,
    @SerializedName("dosage")
    val dosage: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("administred")
    val administred: Int = 0
)
