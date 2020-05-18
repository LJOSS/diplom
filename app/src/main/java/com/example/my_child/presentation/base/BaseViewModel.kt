package com.example.my_child.presentation.base

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.response.ParentResponse
import com.example.my_child.data.api.dto.response.TeacherResponse
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_LOGGED
import com.example.my_child.utils.Constants.IS_PARENT
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BaseViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun getTeacherData(idTeacher: Int): Single<TeacherResponse> =
        myChildApi.getTeacherData(idTeacher)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getParentData(idParent: Int): Single<ParentResponse> =
        myChildApi.getParentData(idParent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun isParent(): Boolean =
        preferencesManager.getBoolean(IS_PARENT, false)

    fun logout() {
        preferencesManager.saveBoolean(IS_LOGGED, false)
    }
}