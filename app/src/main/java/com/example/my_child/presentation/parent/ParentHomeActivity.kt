package com.example.my_child.presentation.parent

import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.parent.home.ParentHomeFragment
import com.example.my_child.presentation.parent.home.ParentHomeFragment.Companion.ParentHomeFragment_TAG
import com.example.my_child.presentation.teacher.classlist.ClassListFragment
import com.example.my_child.utils.setupVisibility
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.navigation_menu_layout.*
import kotlinx.android.synthetic.main.top_bar.*

class ParentHomeActivity : BaseHomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val viewModel = ViewModelProvider(this, BaseViewModelFactory())
            .get(BaseViewModel::class.java)

        initView(viewModel)
        initTopBar()
        initNavigationMenu()
        initBottomNavigation(viewModel)
        openFragment(ParentHomeFragment.newInstance(), ParentHomeFragment_TAG)
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(ParentHomeFragment.newInstance(), ParentHomeFragment_TAG)
                }
                R.id.chat -> {
                }
                R.id.profile -> {
                    //openFragment(ParentProfileFragment.newInstance(), ParentProfileFragment_TAG)
                    viewModel.logout()
                    logout(this)
                }
            }
            true
        }
    }

    private fun initNavigationMenu() {
        nav_home.setOnClickListener { }
        nav_chat.setOnClickListener { }
        nav_homework.setOnClickListener { }
        nav_photos.setOnClickListener { }
        nav_settings.setOnClickListener { }
    }

    private fun initTopBar() {
        burger.setOnClickListener { drawer_layout.openDrawer(Gravity.END) }
    }

    private fun initView(viewModel: BaseViewModel) {
        nav_list_childrens.setupVisibility(false)
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
        this.finish()
    }
}
