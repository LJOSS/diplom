package com.example.my_child.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.App
import com.example.my_child.data.api.MyChildApi
import javax.inject.Inject

class SignUpViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var myChildApi: MyChildApi

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.applicationComponent.inject(this)
        return modelClass
            .getConstructor(MyChildApi::class.java)
            .newInstance(myChildApi)
    }
}