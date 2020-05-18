package com.example.my_child.presentation.teacher

import android.os.Bundle
import android.view.Gravity
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
        openFragment(TeacherHomeFragment.newInstance(), TeacherHomeFragment_TAG)
    }

    private fun initNavigationMenu() {
        nav_home.setOnClickListener { }
        nav_chat.setOnClickListener { }
        nav_homework.setOnClickListener { }
        nav_photos.setOnClickListener { }
        nav_settings.setOnClickListener { }
        nav_list_childrens.setOnClickListener {
            openFragment(
                ClassListFragment.newInstance(),
                ClassListFragment_TAG
            )
        }
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.initBottomNavigation(
            home = {
                openFragment(TeacherHomeFragment.newInstance(), TeacherHomeFragment_TAG)
            },
            chat = {},
            profile = {
                //openFragment(TeacherProfileFragment.newInstance(), TeacherProfileFragment_TAG)
                viewModel.logout()
                logout(this@TeacherHomeActivity)
            }
        )
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
