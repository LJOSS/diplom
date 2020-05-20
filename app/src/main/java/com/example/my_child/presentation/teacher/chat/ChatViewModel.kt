package com.example.my_child.presentation.teacher.chat

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.ChatData
import com.example.my_child.data.api.dto.response.ChatDataResponse
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.domain.date.DateManager
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_PARENT
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ChatViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager,
    private val dateManager: DateManager
) : ViewModel() {

    fun getChat(teacherId: Int, childId: Int): Observable<List<ChatDataResponse>> =
        myChildApi.getChat(teacherId, childId)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun isParent(): Boolean =
        preferencesManager.getBoolean(IS_PARENT, false)

    fun getNumberSender(): Int =
        if (isParent()) {
            0
        } else {
            1
        }

    fun getDate(): String =
        dateManager.getDateForChat()

    fun sendMessage(chatData: ChatData): Single<DefaultResponse> =
        myChildApi
            .insertChat(chatData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}