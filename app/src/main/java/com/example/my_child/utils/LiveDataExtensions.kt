package com.example.my_child.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner,
                                onSuccess: (T?) -> Unit) {
    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            onSuccess(t)
            removeObserver(this)
        }
    }

    observe(lifecycleOwner, observer)
}

fun <T> LiveData<T>.observeOnce(onSuccess: (T?) -> Unit) {
    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            onSuccess(t)
            try {
                removeObserver(this)
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    observeForever(observer)
}

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner,
                                   onSuccess: (T) -> Unit) {
    observe(lifecycleOwner, Observer {
        if (it != null) {
            onSuccess(it)
        }
    })
}

fun <T> LiveData<T>.observeNotNullOnce(lifecycleOwner: LifecycleOwner,
                                       onSuccess: (T) -> Unit) {
    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            if (t != null) {
                onSuccess(t)
            }
            removeObserver(this)
        }
    }

    observe(lifecycleOwner, observer)
}

fun <T> LiveData<T>.observeNotNullForever(onSuccess: (T) -> Unit) {
    observeForever {
        if (it != null) {
            onSuccess(it)
        }
    }
}