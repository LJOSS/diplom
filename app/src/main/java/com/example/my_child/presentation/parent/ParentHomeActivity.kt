package com.example.my_child.presentation.parent

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.presentation.parent.absent.ParentAbsentFragment
import com.example.my_child.presentation.parent.absent.ParentAbsentFragment.Companion.ParentAbsentFragment_TAG
import com.example.my_child.presentation.parent.date.SelectDateFragment
import com.example.my_child.presentation.parent.date.SelectDateFragment.Companion.SelectDateFragment_TAG
import com.example.my_child.presentation.parent.home.ParentHomeFragment
import com.example.my_child.presentation.parent.home.ParentHomeFragment.Companion.ParentHomeFragment_TAG
import com.example.my_child.presentation.parent.homework.ParentHomeworkFragment
import com.example.my_child.presentation.parent.homework.ParentHomeworkFragment.Companion.ParentHomeworkFragment_TAG
import com.example.my_child.presentation.parent.medicine.ParentMedicineFragment
import com.example.my_child.presentation.parent.medicine.ParentMedicineFragment.Companion.MedicineFragment_TAG
import com.example.my_child.presentation.parent.pickup.ParentPickupFragment
import com.example.my_child.presentation.parent.pickup.ParentPickupFragment.Companion.ParentPickupFragment_TAG
import com.example.my_child.presentation.teacher.chat.ChatActivity
import com.example.my_child.presentation.teacher.settings.SettingsFragment
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
        openFragmentFromActivity(
            ParentHomeFragment.newInstance(userId),
            ParentHomeFragment_TAG,
            false
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
    }

    private fun initBottomNavigation(viewModel: BaseViewModel) {
        bottom_navigation.initBottomNavigation(
            home = {
                openFragmentFromActivity(
                    ParentHomeFragment.newInstance(userId),
                    ParentHomeFragment_TAG
                )
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
                openFragmentFromActivity(
                    SettingsFragment.newInstance(userId),
                    SettingsFragment.SettingsFragment_TAG
                )
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
        nav_home.setOnClickListener {
            openFragmentFromActivity(
                ParentHomeFragment.newInstance(userId),
                ParentHomeFragment_TAG
            )
        }
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
        nav_medicine.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openMedicine(viewModel.getTeacherId())
            }
        }
        nav_diary.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openFragmentFromActivity(
                    SelectDateFragment.newInstance(viewModel.getTeacherId(), userId),
                    SelectDateFragment_TAG
                )
            }
        }
        nav_absent.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
            waitAnimation {
                openAbsent(viewModel.getTeacherId())
            }
        }
    }

    fun openAbsent(teacherId: Int) {
        openFragmentFromActivity(
            ParentAbsentFragment.newInstance(teacherId, userId),
            ParentAbsentFragment_TAG
        )
    }

    fun openMedicine(teacherId: Int) {
        openFragmentFromActivity(
            ParentMedicineFragment.newInstance(teacherId, userId),
            MedicineFragment_TAG
        )
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

    fun openFragmentFromActivity(
        fragment: Fragment,
        tag: String,
        isAddToBackStack: Boolean = true
    ) {
        openFragment(fragment, tag, isAddToBackStack)
    }

    fun openPickup(teacherId: Int) {
        openFragmentFromActivity(
            ParentPickupFragment.newInstance(teacherId, userId),
            ParentPickupFragment_TAG
        )
    }
}
