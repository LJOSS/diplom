package com.example.my_child.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.teacher.settings.SettingsViewModel
import com.example.my_child.presentation.teacher.settings.SettingsViewModelFactory

class ChangeLoginDialog : DialogFragment() {

    companion object {
        fun newInstance(): DialogFragment = ChangeLoginDialog()
        const val CHANGE_LOGIN_DIALOG = "CHANGE_LOGIN_DIALOG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.change_login_dialog, null, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AppCompatDialog(requireContext())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, SettingsViewModelFactory())
            .get(SettingsViewModel::class.java)
    }
}