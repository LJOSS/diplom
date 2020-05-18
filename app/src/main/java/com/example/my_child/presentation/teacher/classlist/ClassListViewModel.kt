package com.example.my_child.presentation.teacher.classlist

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.preferences.PreferencesManager

class ClassListViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
}