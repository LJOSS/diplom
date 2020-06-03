package com.example.my_child.presentation.teacher.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.App
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.ValidationManager
import com.example.my_child.domain.preferences.PreferencesManager
import javax.inject.Inject

class SettingsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    lateinit var validationManager: ValidationManager

    @Inject
    lateinit var myChildApi: MyChildApi

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.applicationComponent.inject(this)
        return modelClass
            .getConstructor(
                PreferencesManager::class.java,
                ValidationManager::class.java,
                MyChildApi::class.java
            )
            .newInstance(
                preferencesManager,
                validationManager,
                myChildApi
            )
    }
}