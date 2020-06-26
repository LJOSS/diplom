package com.example.my_child.presentation.teacher.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.presentation.dialog.ChangeLoginDialog
import com.example.my_child.presentation.dialog.ChangeLoginDialog.Companion.CHANGE_LOGIN_DIALOG
import com.example.my_child.presentation.dialog.ChangePasswordDialog
import com.example.my_child.presentation.dialog.ChangePasswordDialog.Companion.CHANGE_PASSWORD_DIALOG
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.ParentHomeActivity
import com.example.my_child.presentation.signup.WelcomeActivity
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.utils.Constants.IS_PARENT
import com.example.my_child.utils.Constants.USER_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance(userId: Int, isParent: Int): SettingsFragment =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                    putInt(IS_PARENT, isParent)
                }
            }

        const val SettingsFragment_TAG = "SettingsFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.settings_fragment, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, SettingsViewModelFactory())
            .get(SettingsViewModel::class.java)

        initView(viewModel)

        remember_me_SW.setOnClickListener {

        }
        password_LL.setOnClickListener {
            ChangePasswordDialog
                .newInstance()
                .show(requireActivity().supportFragmentManager, CHANGE_PASSWORD_DIALOG)
        }
        login_LL.setOnClickListener {
            ChangeLoginDialog
                .newInstance()
                .show(requireActivity().supportFragmentManager, CHANGE_LOGIN_DIALOG)
        }
        logout_LL.setOnClickListener {
            viewModel.logout()
            startActivity(
                Intent(
                    requireActivity(),
                    WelcomeActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
    }

    private fun initView(viewModel: SettingsViewModel) {
        if (isParent == 0) {
            disposable.add(
                viewModel
                    .getParentData(userId)
                    .subscribe({
                        with(it.data) {
                            loadPhoto(profilePicture, profile_CIV, true)
                            name_TV.text = "$fName $lName"
                        }
                    }, Throwable::printStackTrace)
            )
        } else {
            disposable.add(
                viewModel
                    .getTeacherData(userId)
                    .subscribe({
                        with(it.data) {
                            loadPhoto(profilePicture, profile_CIV, false)
                            name_TV.text = "$fName $lName"
                        }
                    }, Throwable::printStackTrace)
            )
        }
    }

    private fun loadPhoto(src: String, imageView: ImageView, isParent: Boolean) {
        Picasso
            .with(requireContext())
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

    override fun onResume() {
        super.onResume()
        if (isParent == 0) {
            (activity as ParentHomeActivity).setTitle("Настройки")
        } else {
            (activity as TeacherHomeActivity).setTitle("Настройки")
        }
    }
}