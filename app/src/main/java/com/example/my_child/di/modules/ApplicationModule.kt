package com.example.my_child.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = application

}