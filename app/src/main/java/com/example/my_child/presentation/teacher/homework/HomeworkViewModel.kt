package com.example.my_child.presentation.teacher.homework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.HomeworkData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.data.api.dto.response.HomeworkDataResponse
import com.example.my_child.domain.date.DateManager
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeworkViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager,
    private val dateManager: DateManager
) : ViewModel() {

    private val liveHomeworkData = MutableLiveData<HomeworkData>()

    fun getHomework(userId: Int): Single<List<HomeworkDataResponse>> =
        myChildApi
            .getHomework(userId)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addHomework(homeworkData: HomeworkData): Single<DefaultResponse> =
        myChildApi.addHomework(homeworkData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun postHomework(homeworkData: HomeworkData) {
        liveHomeworkData.postValue(homeworkData)
    }

    fun getHomework(): LiveData<HomeworkData> = liveHomeworkData

    fun getDate(): String = dateManager.getDateForHomework()

}