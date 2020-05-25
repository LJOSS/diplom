package com.example.my_child.presentation.parent.absent

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.preferences.PreferencesManager

class AbsentViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

}