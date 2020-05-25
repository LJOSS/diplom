package com.example.my_child.data.api.dto.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class ParentDataResponse(
    @SerializedName("id_parent")
    val childId: Int,
    @SerializedName("teacher_id")
    val teacherId: Int,
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
    @SerializedName("Adress_street")
    val address: String,
    @SerializedName("Adress_rajon")
    val address_rajon: String,
    @SerializedName("Mail")
    val mail: String,
    @SerializedName("profile_pic")
    val profilePicture: String
) : Parcelable