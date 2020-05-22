package com.example.my_child.presentation.parent.pickup

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.domain.preferences.PreferencesManager

class PickupViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
):ViewModel() {
}