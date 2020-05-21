package com.example.my_child.presentation.teacher.diary.babydiary

import android.app.TimePickerDialog
import android.widget.TextView
import com.example.my_child.App
import com.example.my_child.R
import com.example.my_child.presentation.fragments.BaseFragment
import java.util.*

open class BaseDiaryFragment : BaseFragment() {

    private val calendar = Calendar.getInstance()

    protected val hour = calendar.get(Calendar.HOUR_OF_DAY)
    protected val min = calendar.get(Calendar.MINUTE)

    protected var sendTime = System.currentTimeMillis()

    private fun setSendTime(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
        calendar[Calendar.MINUTE] = minute
        sendTime = calendar.timeInMillis
    }

    protected fun setTime(hour: Int, min: Int): String {
        val m = if (min < 10) {
            "0$min"
        } else {
            "$min"
        }
        return App.applicationComponent.applicationContext()
            .getString(R.string.time_template, hour, m)
    }

    protected fun closeFragment(){
        requireFragmentManager().popBackStack()
    }

    protected fun initCal(
        time: TextView,
        hour: Int,
        min: Int,
        isSendTime: Boolean
    ) {
        val timePickerDialog = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
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