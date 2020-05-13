package com.example.my_child.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.MedicineDataResponse
import com.example.my_child.domain.ValidationManager
import com.example.my_child.domain.model.error.ValidationResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class SignUpViewModel(
    private val myChildApi: MyChildApi,
    private val validationManager: ValidationManager
) : ViewModel() {

    fun getMedicine(): Single<List<MedicineDataResponse>> =
        myChildApi
            .getMedicine(1, 1)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertMedicine(medicineData: MedicineData): Single<DefaultResponse> =
        myChildApi
            .insertMedicine(medicineData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addPhoto(body: MultipartBody.Part, name: String): Single<DefaultResponse> =
        myChildApi
            .addPhoto(body, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun checkLoginValidation(login: String): ValidationResponse =
        validationManager.checkLoginValidation(login)

    fun checkPasswordValidation(password: String): ValidationResponse =
        validationManager.checkPasswordValidation(password)

}