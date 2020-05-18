package com.example.my_child.utils

import android.os.Handler
import com.example.my_child.BuildConfig

fun postDelayed(delayMillis: Long, action: () -> Unit): Boolean {
    return Handler().postDelayed(action, delayMillis)
}

fun waitAnimation(action: () -> Unit): Boolean {
    return postDelayed(BuildConfig.ANIMATION_TIME_OUT, action)
}
