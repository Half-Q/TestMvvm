package com.half.test.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtil {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun format(data: Date): String {
        return dateFormat.format(data)
    }

    fun format(timestamp: Long): String {
        return dateFormat.format(Date(timestamp))
    }

}