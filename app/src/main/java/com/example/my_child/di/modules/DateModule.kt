package com.example.my_child.di.modules

import com.example.my_child.domain.date.DateManager
import dagger.Module
import dagger.Provides

@Module
class DateModule {
    @Provides
    fun provideDateManager(): DateManager = DateManager()
}