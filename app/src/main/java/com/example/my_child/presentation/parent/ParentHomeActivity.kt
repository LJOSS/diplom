package com.example.my_child.presentation.parent

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.presentation.base.BaseHomeActivity
import com.example.my_child.presentation.base.BaseViewModel
import com.example.my_child.presentation.base.BaseViewModelFactory
import com.example.my_child.utils.debugLog
import kotlinx.android.synthetic.main.activity_home.*
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
    }

    private fun initTopBar() {
        burger.setOnClickListener { drawer_layout.openDrawer(Gravity.END) }
    }

    private fun initView(viewModel: BaseViewModel) {
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.END)) {
            drawer_layout.closeDrawer(Gravity.END)
            return
        }
        this.finish()
    }
}
