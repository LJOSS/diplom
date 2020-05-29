package com.example.my_child.presentation.teacher.settings

import androidx.lifecycle.ViewModel
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_LOGGED

class SettingsViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun logout() {
        preferencesManager.saveBoolean(IS_LOGGED, false)
    }
}