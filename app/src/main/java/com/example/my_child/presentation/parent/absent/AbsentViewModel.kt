package com.example.my_child.presentation.parent.absent

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.response.AbsentDataResponse
import com.example.my_child.data.api.dto.response.AbsentResponse
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.domain.date.DateManager
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AbsentViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager,
    private val dateManager: DateManager
) : ViewModel() {

    fun getAbsent(
        teacherId: Int,
        childId: Int
    ): Single<List<AbsentDataResponse>> =
        myChildApi
            .getAbsent(teacherId, childId)
            .map { it.data.reversed() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertAbsent(absentDataResponse: AbsentDataResponse): Single<DefaultResponse> =
        myChildApi
            .addAbsent(absentDataResponse)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getDate(): String = dateManager.getDateForAbsent()

    fun convertDate(year: Int, month: Int, day: Int): String =
        dateManager.convertDate(year, month, day)
}