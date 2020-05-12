package com.example.my_child.data.api

import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.MedicineResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface MyChildApi {

    @GET("getMedicine.php")
    fun getMedicine(
        @Query("idTeacher") idTeacher: Int,
        @Query("idParent") idParent: Int
    ): Single<MedicineResponse>

    @POST("insertMedicine.php")
    fun insertMedicine(@Body medicineData: MedicineData): Single<DefaultResponse>

    @Multipart
    @POST("iPhoto.php")
    fun addPhoto(body: MultipartBody.Part, name: String): Single<DefaultResponse>
}