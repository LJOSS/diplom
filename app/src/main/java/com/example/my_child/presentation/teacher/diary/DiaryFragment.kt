package com.example.my_child.presentation.teacher.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID

class DiaryFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): Fragment =
            DiaryFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                    putInt(CHILD_ID, childId)
                }
            }

        const val DiaryFragment_TAG = "DiaryFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_diary, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}