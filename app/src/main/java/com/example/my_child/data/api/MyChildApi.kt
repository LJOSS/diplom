package com.example.my_child.data.api

import com.example.my_child.data.api.dto.data.*
import com.example.my_child.data.api.dto.response.*
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface MyChildApi {

    @POST("loginUser.php")
    fun login(@Body loginData: LoginData): Single<LoginResponse>

    @GET("teacherData.php")
    fun getTeacherData(
        @Query("idTeacher") idTeacher: Int
    ): Single<TeacherResponse>

    @GET("parentData.php")
    fun getParentData(
        @Query("idParent") idParent: Int
    ): Single<ParentResponse>

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

    @GET("getChilds.php")
    fun getClassList(
        @Query("idTeacher") idTeacher: Int
    ): Single<ClassListResponse>

    @GET("getHomeworkById.php")
    fun getHomework(
        @Query("idTeacher") idTeacher: Int
    ): Single<HomeworkResponse>

    @POST("insertHomework.php")
    fun addHomework(@Body homeworkData: HomeworkData): Single<DefaultResponse>

    @POST("getChatById.php")
    fun getChat(
        @Query("idTeacher") teacherId: Int,
        @Query("idParent") childId: Int
    ): Observable<ChatResponse>

    @POST("insertChat.php")
    fun insertChat(@Body chatData: ChatData): Single<DefaultResponse>

    @POST("insertDiary.php")
    fun sendDiary(@Body diaryData: DiaryData): Single<DefaultResponse>

    @POST("getDiary.php")
    fun getDiary(
        @Query("idTeacher") teacherId: Int,
        @Query("idParent") childId: Int
    ): Single<DiaryResponse>

    @GET("administeredMedicine.php")
    fun administeredMedicine(@Query("id") id: Int): Single<DefaultResponse>

    @GET("getPickup.php")
    fun getPickup(
        @Query("idTeacher") teacherId: Int,
        @Query("idParent") childId: Int
    ): Single<PickupResponse>

    @POST("addPickup.php")
    fun addPickup(@Body pickupData: PickupData): Single<DefaultResponse>

    @POST("addAbsent.php")
    fun addAbsent(@Body absentDataResponse: AbsentDataResponse): Single<DefaultResponse>

    @GET("getAbsent.php")
    fun getAbsent(
        @Query("idTeacher") teacherId: Int,
        @Query("idParent") childId: Int
    ): Single<AbsentResponse>

    @GET("changePassword.php")
    fun changePassword(
        @Query("oldPassword") oldPassword: String,
        @Query("newPassword") newPassword: String
    ): Single<DefaultResponse>

    @GET("changeLogin.php")
    fun changeLogin(
        @Query("oldLogin") oldLogin: String,
        @Query("newLogin") newLogin: String
    ): Single<DefaultResponse>

}