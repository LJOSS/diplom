package com.example.my_child.domain.date

import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    private val cal = Calendar.getInstance()

    fun getDateForHomework(): String =
        SimpleDateFormat("dd MMMM").format(cal.time)


    fun getDateForChat(): String =
        SimpleDateFormat("dd-MM-yyyy HH:MM").format(cal.time)

    fun getDateForAbsent(): String =
        SimpleDateFormat("dd-MMMM-yyyy").format(cal.time)

    fun convertDate(year: Int, month: Int, day: Int): String {
        val q = SimpleDateFormat("dd-MM-yyyy").parse("$day-$month-$year")
        return SimpleDateFormat("dd-MMMM-yyyy").format(q.time)
    }
}