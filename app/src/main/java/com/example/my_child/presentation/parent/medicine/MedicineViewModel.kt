package com.example.my_child.presentation.parent.medicine

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.MedicineDataResponse
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MedicineViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun insertMedicine(data: MedicineData): Single<DefaultResponse> =
        myChildApi.insertMedicine(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMedicine(idTeacher: Int, idChild: Int): Single<List<MedicineData>> =
        myChildApi
            .getMedicine(idTeacher, idChild)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}