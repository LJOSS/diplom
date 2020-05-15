package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

class TeacherResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: TeacherDataResponse,
    @SerializedName("code")
    val code: Int
)

data class TeacherDataResponse(
    @SerializedName("id_teacher")
    val id: Int,
    @SerializedName("TeacherFirstName")
    val fName: String,
    @SerializedName("TeacherLastName")
    val lName: String,
    @SerializedName("profile_pic")
    val profilePicture: String
)