package com.example.my_child.presentation.teacher.settings

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.dto.data.LoginData
import com.example.my_child.domain.preferences.PreferencesManager
import com.example.my_child.utils.Constants.IS_LOGGED
import com.example.my_child.utils.Constants.LOGIN
import com.example.my_child.utils.Constants.PASSWORD

class SettingsViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun logout() {
        preferencesManager.saveBoolean(IS_LOGGED, false)
    }

    fun getLoginData(): LoginData =
        LoginData(
            userName = preferencesManager.getString(LOGIN) ?: "",
            password = preferencesManager.getString(PASSWORD) ?: ""
        )
}