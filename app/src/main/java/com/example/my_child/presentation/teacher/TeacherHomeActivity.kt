package com.example.my_child.presentation.teacher

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.TeacherDataResponse
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.teacher.classlist.ClassListFragment
import com.example.my_child.presentation.teacher.classlist.ClassListFragment.Companion.ClassListFragment_TAG
import com.example.my_child.presentation.teacher.home.TeacherHomeFragment
import com.example.my_child.presentation.teacher.home.TeacherHomeFragment.Companion.TeacherHomeFragment_TAG
import com.example.my_child.presentation.teacher.homework.TeacherHomeworkFragment
import com.example.my_child.presentation.teacher.homework.TeacherHomeworkFragment.Companion.TeacherHomeworkFragment_TAG
import com.example.my_child.utils.waitAnimation
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
        // TODO NEEED TO TEST FIX
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
        openFragment(TeacherHomeFragment.newInstance(userId), TeacherHomeFragment_TAG)
    }

    private fun initNavigationMenu() {
        nav_home.setOnClickListener { }
        nav_chat.setOnClickListener { }
        nav_homework.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openFragment(TeacherHomeworkFragment.newInstance(userId), TeacherHomeworkFragment_TAG)
            }
        }
        nav_photos.setOnClickListener { }
        nav_settings.setOnClickListener { }
        nav_list_childrens.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openFragment(
                    ClassListFragment.newInstance(userId),
                    ClassListFragment_TAG
                )
            }
        }
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.initBottomNavigation(
            home = {
                openFragment(TeacherHomeFragment.newInstance(userId), TeacherHomeFragment_TAG)
            },
            chat = {},
            profile = {
                //openFragment(TeacherProfileFragment.newInstance(), TeacherProfileFragment_TAG)
                viewModel.logout()
                logout(this@TeacherHomeActivity)
            }
        )
    }

    fun openFragmentFromFragment(fragment: Fragment, tag: String) {
        openFragment(fragment, tag)
    }

    private fun initTopBar() {
        burger.setOnClickListener { drawer_layout.openDrawer(Gravity.END) }
    }

    private fun initView(viewModel: BaseViewModel) {
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
}
