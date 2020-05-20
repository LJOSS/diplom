package com.example.my_child.presentation.parent

import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.parent.home.ParentHomeFragment
import com.example.my_child.presentation.parent.home.ParentHomeFragment.Companion.ParentHomeFragment_TAG
import com.example.my_child.presentation.parent.homework.ParentHomeworkFragment
import com.example.my_child.presentation.parent.homework.ParentHomeworkFragment.Companion.ParentHomeworkFragment_TAG
import com.example.my_child.presentation.teacher.chat.ChatActivity
import com.example.my_child.utils.setupVisibility
import com.example.my_child.utils.waitAnimation
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
        initNavigationMenu(viewModel)
        initBottomNavigation(viewModel)
        openFragmentFromActivity(ParentHomeFragment.newInstance(), ParentHomeFragment_TAG, false)
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.initBottomNavigation(
            home = {
                openFragmentFromActivity(ParentHomeFragment.newInstance(), ParentHomeFragment_TAG)
            },
            chat = {
                startActivity(
                    ChatActivity.newInstance(
                        viewModel.getTeacherId(),
                        userId,
                        this
                    )
                )
            },
            profile = {
                //openFragment(ParentProfileFragment.newInstance(), ParentProfileFragment_TAG)
                viewModel.logout()
                logout(this)
            }
        )
    }

    fun openChat(teacherId: Int) {
        startActivity(
            ChatActivity.newInstance(
                teacherId,
                userId,
                this
            )
        )
    }

    private fun initNavigationMenu(viewModel: BaseViewModel) {
        nav_home.setOnClickListener { }
        nav_chat.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openChat(viewModel.getTeacherId())
            }
        }
        nav_homework.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openFragmentFromActivity(
                    ParentHomeworkFragment.newInstance(viewModel.getTeacherId()),
                    ParentHomeworkFragment_TAG
                )
            }
        }
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
                loadPhoto(profilePicture, profile_pic, true)
                profile_name.text =
                    getString(
                        R.string.profile_name_template,
                        this.fName,
                        this.lName
                    )
                viewModel.saveTeacherId(teacherId)
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

    fun openFragmentFromActivity(
        fragment: Fragment,
        tag: String,
        isAddToBackStack: Boolean = true
    ) {
        openFragment(fragment, tag, isAddToBackStack)
    }
}
