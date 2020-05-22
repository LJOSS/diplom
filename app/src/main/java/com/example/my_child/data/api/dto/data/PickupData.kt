package com.example.my_child.data.api.dto.data

import com.google.gson.annotations.SerializedName

data class PickupData(
    @SerializedName("id_parent")
    val childId: Int,
    @SerializedName("id_teacher")
    val teacherId: Int,
    @SerializedName("parent_name")
    val parentName: String,
    @SerializedName("relation")
    val relation: String,
    @SerializedName("vehicle_number")
    val vehicle_number: String,
    @SerializedName("contact_number")
    val contact_nmber: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("time")
    val time: Long
)