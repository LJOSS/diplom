package com.example.my_child.presentation.teacher.classlist

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.response.ClassListResponse
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClassListViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun getClassList(idTeacher: Int): Single<ClassListResponse> =
        myChildApi
            .getClassList(idTeacher)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}