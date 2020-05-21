package com.example.my_child.presentation.teacher.diary

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.DiaryData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.DiaryResponse
import com.example.my_child.domain.date.DateManager
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiaryViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager,
    private val dateManager: DateManager
) : ViewModel() {

    fun sendDiary(diaryData: DiaryData): Single<DefaultResponse> =
        myChildApi.sendDiary(diaryData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getDiary(teacherId: Int, childId: Int): Single<List<DiaryData>> =
        myChildApi.getDiary(teacherId, childId)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}