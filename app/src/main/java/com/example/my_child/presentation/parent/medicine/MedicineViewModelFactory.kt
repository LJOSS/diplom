package com.example.my_child.presentation.parent.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.App
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.preferences.PreferencesManager
import javax.inject.Inject

class MedicineViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var myChildApi: MyChildApi

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.applicationComponent.inject(this)

        return modelClass
            .getConstructor(MyChildApi::class.java, PreferencesManager::class.java)
            .newInstance(myChildApi, preferencesManager)
    }
}