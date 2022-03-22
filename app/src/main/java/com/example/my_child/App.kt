package com.example.my_child

import androidx.multidex.MultiDexApplication
import com.example.my_child.di.components.ApplicationComponent
import com.example.my_child.di.components.DaggerApplicationComponent
import com.example.my_child.di.modules.ApplicationModule

class App : MultiDexApplication() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // Create Test Branch
        // Create Test Branch 2
        // Commit Test Branch 2
        // Commit 2 Test Branch 2
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}