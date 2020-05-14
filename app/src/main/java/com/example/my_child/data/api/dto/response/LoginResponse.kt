package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<LoginDataResponse>,
    @SerializedName("code")
    val code: Int
)

data class LoginDataResponse(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("parent_teacher")
    val parent_teacher: Int
) {
    fun isParent(): Boolean =
        parent_teacher == 0
}
