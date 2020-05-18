package com.example.my_child.presentation.teacher

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.data.api.dto.response.TeacherDataResponse
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.teacher.home.HomeFragment
import com.example.my_child.presentation.teacher.home.HomeFragment.Companion.HomeFragment_TAG
import com.example.my_child.utils.debugLog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.navigation_menu_layout.*
import kotlinx.android.synthetic.main.top_bar.*

class TeacherHomeActivity : BaseHomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val viewModel = ViewModelProvider(this, BaseViewModelFactory())
            .get(BaseViewModel::class.java)

        initView(viewModel)
        initTopBar()
        initBottomNavigation(viewModel)
        initNavigationMenu()
        openFragment(HomeFragment.newInstance(), HomeFragment_TAG)
    }

    private fun initNavigationMenu() {
        nav_home.setOnClickListener { }
        nav_chat.setOnClickListener { }
        nav_homework.setOnClickListener { }
        nav_photos.setOnClickListener { }
        nav_settings.setOnClickListener { }
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(HomeFragment.newInstance(), HomeFragment_TAG)
                }
                R.id.chat -> {
                }
                R.id.profile -> {
                    viewModel.logout()
                    logout(this@TeacherHomeActivity)
                }
            }
            true
        }
    }

    public fun openFragment(fragment: Fragment, tag: String) {
        if (supportFragmentManager.fragments.isNotEmpty()) {
            val lastFragment = getLastFragment()
            if (lastFragment.tag != tag) {
                debugLog("ADD_FAGMENT")
                addFragment(fragment, tag)
            }
        } else {
            addFragment(fragment, tag)
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
            .commit()
    }

    private fun initTopBar() {
        burger.setOnClickListener { drawer_layout.openDrawer(Gravity.END) }
    }

    private fun initView(viewModel: BaseViewModel) {
        if (viewModel.isParent()) {
            getParentData(viewModel) {
                with(it) {
                    loadPhoto(profilePicture, profile_pic)
                    profile_name.text =
                        getString(
                            R.string.profile_name_template,
                            this.fName,
                            this.lName
                        )
                }
            }
        } else {
            getTeacherData(viewModel) {
                with(it) {
                    loadPhoto(profilePicture, profile_pic)
                    profile_name.text =
                        getString(
                            R.string.profile_name_template,
                            this.fName,
                            this.lName
                        )
                }
            }
        }
    }

    private fun getTeacherData(viewModel: BaseViewModel, function: (TeacherDataResponse) -> Unit) {
        disposable.add(
            viewModel
                .getTeacherData(userId)
                .subscribe({
                    if (it.code == 3) {
                        logout(this)
                    } else {
                        function(it.data)
                    }
                }, Throwable::printStackTrace)
        )
    }

    private fun getParentData(viewModel: BaseViewModel, function: (ParentDataResponse) -> Unit) {
        disposable.add(
            viewModel
                .getParentData(userId)
                .subscribe({
                    if (it.code == 3) {
                        logout(this)
                    } else {
                        function(it.data)
                    }
                }, Throwable::printStackTrace)
        )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.END)) {
            drawer_layout.closeDrawer(Gravity.END)
            return
        }
        debugLog("SIZE_ ${supportFragmentManager.fragments.size}")
        val lastFragment = getLastFragment()
        if (supportFragmentManager.fragments.size == 1) {
            this.finish()
        } else {
            supportFragmentManager.beginTransaction().remove(lastFragment).commit()
        }
    }

    private fun getLastFragment(): Fragment = supportFragmentManager.fragments.last()
}
