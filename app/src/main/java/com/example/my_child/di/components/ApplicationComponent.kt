package com.example.my_child.di.components

import android.content.Context
import com.example.my_child.di.modules.*
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.signup.SignUpViewModelFactory
import com.example.my_child.presentation.teacher.chat.ChatViewModelFactory
import com.example.my_child.presentation.teacher.classlist.ClassListViewModelFactory
import com.example.my_child.presentation.teacher.homework.HomeworkViewModelFactory
import com.example.my_child.presentation.teacher.selectchild.SelectChildViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        ApiModule::class,
        SignUpModule::class,
        PreferenceModule::class,
        DateModule::class
    )
)
interface ApplicationComponent {
    fun applicationContext(): Context

    fun inject(signUpViewModelFactory: SignUpViewModelFactory)

    fun inject(signUpViewModelFactory: BaseViewModelFactory)

    fun inject(classListViewModelFactory: ClassListViewModelFactory)

    fun inject(homeworkViewModelFactory: HomeworkViewModelFactory)

    fun inject(chatViewModelFactory: ChatViewModelFactory)

    fun inject(selectChildViewModelFactory: SelectChildViewModelFactory)
}