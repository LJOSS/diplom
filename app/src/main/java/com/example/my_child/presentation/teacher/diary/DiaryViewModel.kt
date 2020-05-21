package com.example.my_child.presentation.teacher.diary

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.date.DateManager
import com.example.my_child.domain.preferences.PreferencesManager

class DiaryViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager,
    private val dateManager: DateManager
) : ViewModel() {
}