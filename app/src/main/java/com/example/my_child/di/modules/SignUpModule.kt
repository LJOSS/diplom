package com.example.my_child.di.modules

import com.example.my_child.domain.ValidationManager
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {

    @Provides
    fun provideValidationManager(): ValidationManager = ValidationManager()
}