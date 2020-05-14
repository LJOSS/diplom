package com.example.my_child.presentation

import androidx.appcompat.app.AppCompatActivity
import com.example.my_child.utils.Constants.USER_ID

open class BaseHomeActivity : AppCompatActivity() {

    protected val userId: Int by lazy { getIntArguments(USER_ID) }

    private fun getIntArguments(key: String): Int =
        intent.extras?.getInt(key) ?: throw IllegalStateException("Expect arguments")
}