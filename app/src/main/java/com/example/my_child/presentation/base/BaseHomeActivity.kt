package com.example.my_child.presentation.base

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.presentation.signup.WelcomeActivity
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import com.example.my_child.utils.Constants.USER_ID
import com.example.my_child.utils.debugLog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_home.*

open class BaseHomeActivity : AppCompatActivity() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    private fun getStringArguments(key: String): String =
        intent.extras?.getString(key) ?: throw IllegalStateException("Expect arguments")

    protected val userId: Int by lazy { getIntArguments(USER_ID) }

    protected val teacherId: Int by lazy { getIntArguments(TEACHER_ID) }

    protected val childId: Int by lazy { getIntArguments(CHILD_ID) }

    private fun getIntArguments(key: String): Int =
        intent.extras?.getInt(key) ?: throw IllegalStateException("Expect arguments")

    protected fun loadPhoto(src: String, imageView: ImageView, isParent: Boolean) {
        Picasso
            .with(this)
            .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}$src")
            .error(getErrorDrawable(isParent))
            .into(imageView)
    }

    private fun getErrorDrawable(parent: Boolean): Int =
        if (parent) {
            R.drawable.error_profile_boy
        } else {
            R.drawable.error_teacher
        }

    protected fun logout(activity: Activity) {
        startActivity(
            Intent(
                activity,
                WelcomeActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.END)) {
            drawer_layout.closeDrawer(Gravity.END)
            return
        }
        super.onBackPressed()
    }

    private fun getLastFragment(): Fragment = supportFragmentManager.fragments.last()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    protected fun openFragment(fragment: Fragment, tag: String, isAddToBackStack: Boolean = true) {
        if (supportFragmentManager.fragments.isNotEmpty()) {
            val lastFragment = getLastFragment()
            if (lastFragment.tag != tag) {
                debugLog("ADD_FAGMENT")
                addFragment(fragment, tag, isAddToBackStack)
            }
        } else {
            addFragment(fragment, tag, isAddToBackStack)
        }
    }

    private fun addFragment(fragment: Fragment, tag: String, isAddToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
        if (isAddToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    protected fun BottomNavigationView.initBottomNavigation(
        home: () -> Unit,
        chat: () -> Unit,
        profile: () -> Unit
    ) {
        this.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    home()
                }
                R.id.chat -> {
                    chat()
                }
                R.id.profile -> {
                    profile()
                }
            }
            true
        }
    }
}