package com.example.my_child.presentation.parent

import android.os.Bundle
import android.widget.Toast
import com.example.my_child.R
import com.example.my_child.presentation.BaseHomeActivity
import com.example.my_child.utils.debugLog

class ParentHomeActivity : BaseHomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Toast.makeText(this, userId.toString(), Toast.LENGTH_LONG).show()
        debugLog("USER_ID${userId}")
    }
}
