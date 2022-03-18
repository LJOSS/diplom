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
        //Develop Branch
        //Test Branch Test Commit
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}