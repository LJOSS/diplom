package com.example.my_child.data.api

import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.MedicineResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyChildApi {

    @GET("getMedicine.php")
    fun getMedicine(
        @Query("idTeacher") idTeacher: Int,
        @Query("idParent") idParent: Int
    ): Single<MedicineResponse>

    @POST("insertMedicine.php")
    fun insertMedicine(@Body medicineData: MedicineData): Single<DefaultResponse>
}