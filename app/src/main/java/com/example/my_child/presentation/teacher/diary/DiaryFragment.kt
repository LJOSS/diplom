package com.example.my_child.presentation.teacher.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.presentation.teacher.diary.DiaryHistoryFragment.Companion.DiaryHistoryFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.*
import com.example.my_child.presentation.teacher.diary.babydiary.FecesFragment.Companion.FecesFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.SleepFragment.Companion.SleepFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.SoftFoodFragment.Companion.SoftFoodFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.SolidFoodFragment.Companion.SolidFoodFragment_TAG
import com.example.my_child.presentation.teacher.diary.babydiary.UrineFragment.Companion.UrineFragment_TAG
import com.example.my_child.utils.Constants.BASE_DATE_FORMAT
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import com.example.my_child.utils.getCurrentDate
import kotlinx.android.synthetic.main.fragment_diary.*
import java.text.SimpleDateFormat
import java.util.*

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
        feces_LL.setOnClickListener {
            openFragment(FecesFragment.newInstance(teacherId, childId), FecesFragment_TAG)
        }
        food_LL.setOnClickListener {
            openFragment(SoftFoodFragment.newInstance(teacherId, childId), SoftFoodFragment_TAG)
        }
        solid_LL.setOnClickListener {
            openFragment(SolidFoodFragment.newInstance(teacherId, childId), SolidFoodFragment_TAG)
        }
        urine_LL.setOnClickListener {
            openFragment(UrineFragment.newInstance(teacherId, childId), UrineFragment_TAG)
        }
        sleep_LL.setOnClickListener {
            openFragment(SleepFragment.newInstance(teacherId, childId), SleepFragment_TAG)
        }
        history_LL.setOnClickListener {
            openFragment(
                DiaryHistoryFragment.newInstance(teacherId, childId, getCurrentDate()),
                DiaryHistoryFragment_TAG
            )
        }
    }

    private fun openFragment(fragment: BaseFragment, tag: String) {
        (activity as TeacherHomeActivity).openFragmentFromActivity(fragment, tag)
    }
}