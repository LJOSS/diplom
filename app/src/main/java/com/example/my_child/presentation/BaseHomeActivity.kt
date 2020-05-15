package com.example.my_child.presentation

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.utils.Constants.USER_ID
import com.squareup.picasso.Picasso

open class BaseHomeActivity : AppCompatActivity() {

    private fun getStringArguments(key: String): String =
        intent.extras?.getString(key) ?: throw IllegalStateException("Expect arguments")

    protected val userId: Int by lazy { getIntArguments(USER_ID) }

    private fun getIntArguments(key: String): Int =
        intent.extras?.getInt(key) ?: throw IllegalStateException("Expect arguments")

    protected fun loadPhoto(src: String, imageView: ImageView) {
        Picasso
            .with(this)
            .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}$src")
            .error(R.drawable.family)
            .into(imageView)
    }
}