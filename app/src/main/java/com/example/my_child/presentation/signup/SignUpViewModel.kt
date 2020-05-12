package com.example.my_child.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.MedicineDataResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(private val myChildApi: MyChildApi) : ViewModel() {

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
}