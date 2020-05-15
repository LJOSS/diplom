package com.example.my_child.presentation.parent

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.utils.debugLog
import kotlinx.android.synthetic.main.navigation_menu_layout.*

class ParentHomeActivity : BaseHomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val viewModel = ViewModelProvider(this, BaseViewModelFactory())
            .get(BaseViewModel::class.java)
        Toast.makeText(this, userId.toString(), Toast.LENGTH_LONG).show()
        debugLog("USER_ID${userId}")
        initView(viewModel)
    }

    private fun initView(viewModel: BaseViewModel) {
        disposable.add(
            viewModel
                .getParentData(userId)
                .subscribe({
                    if (it.code == 3) {
                        logout(this)
                    } else {

                    }
                }, Throwable::printStackTrace)
        )

        loadPhoto("", profile_pic)
    }
}
