package com.example.my_child.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.LoginData
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.LoginResponse
import com.example.my_child.data.api.dto.response.MedicineDataResponse
import com.example.my_child.domain.ValidationManager
import com.example.my_child.domain.model.error.ValidationResponse
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_LOGGED
import com.example.my_child.utils.Constants.LOGIN
import com.example.my_child.utils.Constants.PASSWORD
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import kotlin.math.log

class SignUpViewModel(
    private val myChildApi: MyChildApi,
    private val validationManager: ValidationManager,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun login(loginData: LoginData): Observable<LoginResponse> =
        myChildApi.login(loginData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

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

    fun saveUser(loginData: LoginData) {
        with(loginData) {
            preferencesManager.saveString(LOGIN, userName)
            preferencesManager.saveString(PASSWORD, password)
        }
        preferencesManager.saveBoolean(IS_LOGGED, true)
    }

    fun isLogged(): Boolean =
        preferencesManager.getBoolean(IS_LOGGED, false)

    fun getLoginData(): LoginData =
        LoginData(
            userName = preferencesManager.getString(LOGIN) ?: "",
            password = preferencesManager.getString(PASSWORD) ?: ""
        )
}