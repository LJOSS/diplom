package com.example.my_child.di.components

import android.content.Context
import com.example.my_child.di.modules.ApiModule
import com.example.my_child.di.modules.ApplicationModule
import com.example.my_child.di.modules.PreferenceModule
import com.example.my_child.di.modules.SignUpModule
import com.example.my_child.presentation.signup.SignUpViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        ApiModule::class,
        SignUpModule::class,
        PreferenceModule::class
    )
)
interface ApplicationComponent {
    fun applicationContext(): Context
    fun inject(signUpViewModelFactory: SignUpViewModelFactory)
}