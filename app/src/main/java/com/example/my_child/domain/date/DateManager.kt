package com.example.my_child.domain.date

import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    fun getDateForHomework(): String {
        val cal: Calendar = Calendar.getInstance()
        return SimpleDateFormat("dd MMMM").format(cal.getTime())
    }
}