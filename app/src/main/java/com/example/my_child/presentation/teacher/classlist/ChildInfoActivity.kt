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
            Picasso
                .with(this@ChildInfoActivity)
                .load("${BuildConfig.MY_CHILD_HOST_PHOTOS}${this.profilePicture}")
                .error(R.drawable.error_profile_boy)
                .into(child_image)
            child_name.text = getString(
                R.string.profile_name_template,
                childFName,
                childLName
            )
            parent_name.text = fName
            parent_last_name.text = lName
            number.text = phone
            address_st.text = "$address, $address_house"
            rajon.text = address_rajon
            mail_p.text = mail
        }
    }

    private fun initTopBar() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { onBackPressed() }
        burger.visibility = View.GONE
    }
}