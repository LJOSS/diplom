package com.example.my_child.presentation.teacher.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.presentation.teacher.homework.TeacherHomeworkFragment
import com.example.my_child.presentation.teacher.homework.TeacherHomeworkFragment.Companion.TeacherHomeworkFragment_TAG
import com.example.my_child.presentation.teacher.selectchild.SelectChildFragment
import com.example.my_child.presentation.teacher.selectchild.SelectChildFragment.Companion.SelectChildFragment_TAG
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.CHAT_FRAGMENT
import com.example.my_child.utils.Constants.DIARY_FRAGMENT
import com.example.my_child.utils.Constants.USER_ID
import kotlinx.android.synthetic.main.fragment_home.*

class TeacherHomeFragment : BaseFragment() {

    companion object {
        fun newInstance(userId: Int): Fragment =
            TeacherHomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }

        const val TeacherHomeFragment_TAG = "TeacherHomeFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClickers()
    }

    private fun initClickers() {
        diary_layout.setOnClickListener {
            openFragment(
                SelectChildFragment.newInstance(userId, DIARY_FRAGMENT),
                SelectChildFragment_TAG
            )
        }
        chat_layout.setOnClickListener {
            openFragment(
                SelectChildFragment.newInstance(userId, CHAT_FRAGMENT),
                SelectChildFragment_TAG
            )
        }
        photo_layout.setOnClickListener {}
        homework_layout.setOnClickListener {
            openFragment(
                TeacherHomeworkFragment.newInstance(userId),
                TeacherHomeworkFragment_TAG
            )
        }
        medicine_layout.setOnClickListener {}
        absent_track_layout.setOnClickListener {}
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        (activity as TeacherHomeActivity).openFragmentFromActivity(fragment, tag)
    }
}