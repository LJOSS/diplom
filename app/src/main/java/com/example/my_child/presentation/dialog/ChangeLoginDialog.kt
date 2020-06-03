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
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.change_login_dialog.*

class ChangeLoginDialog : DialogFragment() {

    private val disposable: CompositeDisposable = CompositeDisposable()

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

        change_login.setOnClickListener {
            if (old_login.text.toString() != viewModel.getLoginData().userName) {
                old_login_text_layout.error = "Логин не совпадает"
            } else if (new_login.text.toString() != repeat_new_login.text.toString()) {
                repeat_new_login_layout.error = "Логины не совпадают"
            } else {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}