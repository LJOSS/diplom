package com.example.my_child.utils

import android.content.res.Resources
import android.util.TypedValue
import com.example.my_child.App
import kotlin.math.roundToInt


fun dpToPx(dp: Int): Int {
    return try {
        val r: Resources = App.applicationComponent.applicationContext().resources
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
}