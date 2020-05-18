package com.example.my_child.utils

import android.view.View
import androidx.core.view.isVisible

fun View.setupVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.setupVisibilityForCheckPointMark(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.changeVisibility() {
    this.visibility = if (this.isVisible) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
