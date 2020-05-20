package com.example.my_child.presentation.teacher.chat

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.response.ChatDataResponse
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun getChat(teacherId: Int, childId: Int): Observable<List<ChatDataResponse>> =
        myChildApi.getChat(teacherId, childId)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}