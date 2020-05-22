package com.example.my_child.presentation.parent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.diary.DiaryHistoryFragment
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.BASE_DATE_FORMAT
import com.example.my_child.utils.Constants.CHILD_ID
import com.example.my_child.utils.Constants.TEACHER_ID
import kotlinx.android.synthetic.main.fragment_select_date.*
import java.text.SimpleDateFormat
import java.util.*

class SelectDateFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): Fragment =
            SelectDateFragment().apply {
                arguments = Bundle().apply {
                    putInt(TEACHER_ID, teacherId)
                    putInt(CHILD_ID, childId)
                }
            }

        const val SelectDateFragment_TAG = "SelectDateFragment_TAG"
    }

    private lateinit var todayDate: String
    private lateinit var formattedDate: String
    private lateinit var c: Calendar
    private lateinit var df: SimpleDateFormat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_select_date, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val c = Calendar.getInstance()
        val df = SimpleDateFormat(BASE_DATE_FORMAT)
        var formattedDate = df.format(c.time)
        val todayDate = formattedDate
        date.text = todayDate
        next.isEnabled = false;
        next.alpha = 0.3f
        next.setOnClickListener {
            c.add(Calendar.DATE, 1)
            formattedDate = df.format(c.time)
            if (todayDate == formattedDate) {
                next.isEnabled = false
                next.alpha = 0.3f
            }
            changeDate(formattedDate)
        }
        previous.setOnClickListener {
            c.add(Calendar.DATE, -1)
            formattedDate = df.format(c.time)
            if (todayDate != formattedDate) {
                next.isEnabled = true
                next.alpha = 1.0f
            }
            changeDate(formattedDate)
        }
        goToBabyFragment(formattedDate)
    }

    private fun changeDate(format: String) {
        date.text = format
        goToBabyFragment(format)
    }

    private fun goToBabyFragment(formattedDate: String) {
        if (activity != null && isAdded) {
            val fragment = DiaryHistoryFragment
                .newInstance(teacherId, childId, formattedDate)
            childFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.diary_layout, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }
}