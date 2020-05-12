package com.example.my_child.data.api

import com.example.my_child.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory {
    val retrofitBuilder: Retrofit.Builder

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()

        retrofitBuilder = Retrofit.Builder()
            .client(okHttpClientBuilder)
            .baseUrl(BuildConfig.MY_CHILD_HOST_WEBSERVISE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    inline fun <reified T> createApi(type: Class<T>): T =
        retrofitBuilder.build().create(type)
}