package com.example.my_child.utils

import android.util.Log
import com.example.my_child.utils.Constants.TAG

fun debugLog(message: String, type: Int = Log.DEBUG) {
    when (type) {
        Log.DEBUG -> Log.d(TAG, message)
        Log.INFO -> Log.i(TAG, message)
        Log.WARN -> Log.w(TAG, message)
        Log.ERROR -> Log.e(TAG, message)
        Log.VERBOSE -> Log.v(TAG, message)
        Log.ASSERT -> Log.wtf(TAG, message)
    }
}