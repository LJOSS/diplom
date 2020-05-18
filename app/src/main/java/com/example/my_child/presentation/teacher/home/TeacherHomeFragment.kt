package com.example.my_child.presentation.teacher.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_child.R
import kotlinx.android.synthetic.main.fragment_home.*

class TeacherHomeFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment =
            TeacherHomeFragment()

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
        diary_layout.setOnClickListener { }
        chat_layout.setOnClickListener {}
        photo_layout.setOnClickListener {}
        homework_layout.setOnClickListener {}
        medicine_layout.setOnClickListener {}
        absent_track_layout.setOnClickListener {}
    }
}