package com.example.my_child.di.modules

import com.example.my_child.data.api.ApiFactory
import com.example.my_child.data.api.MyChildApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiFactory(): ApiFactory =
        ApiFactory()

    @Provides
    @Singleton
    fun provideNOKOApi(factory: ApiFactory): MyChildApi =
        factory.createApi(MyChildApi::class.java)
}