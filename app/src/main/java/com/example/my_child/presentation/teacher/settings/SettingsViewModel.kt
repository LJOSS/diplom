package com.example.my_child.presentation.teacher.settings

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.LoginData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.ParentResponse
import com.example.my_child.data.api.dto.response.TeacherResponse
import com.example.my_child.domain.ValidationManager
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_LOGGED
import com.example.my_child.utils.Constants.LOGIN
import com.example.my_child.utils.Constants.PASSWORD
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingsViewModel(
    private val preferencesManager: PreferencesManager,
    private val validationManager: ValidationManager,
    private val myChildApi: MyChildApi
) : ViewModel() {

    fun getTeacherData(idTeacher: Int): Single<TeacherResponse> =
        myChildApi.getTeacherData(idTeacher)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getParentData(idParent: Int): Single<ParentResponse> =
        myChildApi.getParentData(idParent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun logout() {
        preferencesManager.saveBoolean(IS_LOGGED, false)
    }

    fun getLoginData(): LoginData =
        LoginData(
            userName = preferencesManager.getString(LOGIN) ?: "",
            password = preferencesManager.getString(PASSWORD) ?: ""
        )

    fun changeLogin(
        old: String, new: String
    ): Single<DefaultResponse> =
        myChildApi
            .changeLogin(old, new)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun changePassword(
        old: String, new: String
    ): Single<DefaultResponse> =
        myChildApi
            .changePassword(old, new)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}