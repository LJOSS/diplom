package com.example.my_child.presentation.teacher.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.App
import com.example.my_child.domain.preferences.PreferencesManager
import javax.inject.Inject

class SettingsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.applicationComponent.inject(this)
        return modelClass
            .getConstructor(PreferencesManager::class.java)
            .newInstance(preferencesManager)
    }
}