package com.example.my_child.presentation.teacher.classlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_info_activity.*
import kotlinx.android.synthetic.main.top_bar.*
import java.lang.IllegalStateException

class ChildInfoActivity : AppCompatActivity() {
    companion object {
        const val CHILD_INFO = "CHILD_INFO"
    }

    private val childInfo: ParentDataResponse by lazy {
        intent.extras!!.getParcelable<ParentDataResponse>(CHILD_INFO)
            ?: throw IllegalStateException("Expect arguments")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.child_info_activity)
        initTopBar()
        with(childInfo) {
            //TODO ENTER ERROR PHOTO
            Picasso
                .with(this@ChildInfoActivity)
                .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}${this.profilePicture}")
                .into(child_image)
            child_name.text = getString(
                R.string.profile_name_template,
                childFName,
                childLName
            )
        }
    }

    private fun initTopBar() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { onBackPressed() }
        burger.visibility = View.GONE
    }
}