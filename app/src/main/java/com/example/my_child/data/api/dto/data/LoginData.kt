package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("login")
    val userName: String,
    @SerializedName("password")
    val password: String
)