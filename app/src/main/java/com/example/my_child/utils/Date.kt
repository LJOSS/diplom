package com.example.my_child.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String =
    SimpleDateFormat(Constants.BASE_DATE_FORMAT).format(Calendar.getInstance().time)