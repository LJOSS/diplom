package com.example.my_child.presentation.teacher.diary.babydiary

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.DiaryData
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.teacher.diary.DiaryViewModel
import com.example.my_child.presentation.teacher.diary.DiaryViewModelFactory
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.SLEEP
import kotlinx.android.synthetic.main.fragment_sleep.*

class SleepFragment : BaseDiaryFragment() {

    companion object {
        fun newInstance(teacherId: Int, childId: Int): BaseFragment =
            SleepFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.CHILD_ID, childId)
                }
            }

        const val SleepFragment_TAG = "SleepFragment_TAG"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_sleep, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProvider(this, DiaryViewModelFactory())
            .get(DiaryViewModel::class.java)

        timeFrom.text = setTime(hour, min)
        timeTo.text = setTime(hour, min)

        timeFrom.setOnClickListener { initCal(timeFrom, hour, min, true) }

        timeTo.setOnClickListener { initCal(timeTo, hour, min, false) }

        save.setOnClickListener { saveSleep(timeFrom, timeTo, viewModel) }
    }

    private fun saveSleep(
        timeFrom: TextView,
        timeTo: TextView,
        viewModel: DiaryViewModel
    ) {
        sendSleep(viewModel, "Спал с ${timeFrom.text} по ${timeTo.text}")
        closeFragment()
    }

    private fun sendSleep(
        viewModel: DiaryViewModel,
        message: String
    ) {
        disposable.add(
            viewModel
                .sendDiary(
                    DiaryData(
                        childId = childId,
                        teacherId = teacherId,
                        message = message,
                        type = SLEEP,
                        time = sendTime
                    )
                )
                .subscribe({

                }, Throwable::printStackTrace)
        )
    }

    private fun initCal(
        time: TextView,
        hour: Int,
        min: Int,
        isSendTime: Boolean
    ) {
        val timePickerDialog = TimePickerDialog(
            context,
            OnTimeSetListener { _, hourOfDay, minute ->
                time.text = setTime(hourOfDay, minute)
                if (isSendTime) {
                    setSendTime(hourOfDay, minute)
                }
            },
            hour,
            min,
            true
        )
        timePickerDialog.show()
    }

}