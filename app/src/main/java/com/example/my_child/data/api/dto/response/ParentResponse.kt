package com.example.my_child.data.api.dto.response

import com.google.gson.annotations.SerializedName

class ParentResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: ParentDataResponse,
    @SerializedName("code")
    val code: Int
)

data class ParentDataResponse(
    @SerializedName("id_teacher")
    val id: Int,
    @SerializedName("FirstName")
    val fName: String,
    @SerializedName("LastName")
    val lName: String,
    @SerializedName("ChildFirstName")
    val childFName: String,
    @SerializedName("ChildLastName")
    val childLName: String,
    @SerializedName("PhoneNumber")
    val phone: String,
    @SerializedName("Adress")
    val address: String,
    @SerializedName("Mail")
    val mail: String,
    @SerializedName("profile_pic")
    val profilePicture: String
)