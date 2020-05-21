package com.example.my_child.presentation.teacher.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.presentation.teacher.diary.DiaryHistoryFragment.Companion.DiaryHistoryFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.SleepFragment
import com.example.my_child.presentation.teacher.diary.babydiary.SleepFragment.Companion.SleepFragment_TAG
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
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
        food_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        solid_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        urine_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        feces_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        sleep_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        history_LL.setOnClickListener {
            openFragment(DiaryHistoryFragment.newInstance(teacherId, childId), DiaryHistoryFragment_TAG)
        }
    }

    private fun openFragment(fragment: BaseFragment, tag: String) {
        (activity as TeacherHomeActivity).openFragmentFromActivity(fragment, tag)
    }
}