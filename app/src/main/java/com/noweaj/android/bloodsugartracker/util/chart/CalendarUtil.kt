package com.noweaj.android.bloodsugartracker.util.chart

import java.util.*

object CalendarUtil {
    
    fun getStartOfDay(): Long{
        return getStartOfDay(Calendar.getInstance().timeInMillis) 
    }
    
    fun getStartOfDay(timeInMillis: Long): Long{
        val calendarNow = Calendar.getInstance().clone() as Calendar
        calendarNow.timeInMillis = timeInMillis
        calendarNow.set(Calendar.HOUR_OF_DAY, 0)
        calendarNow.set(Calendar.MINUTE, 0)
        calendarNow.set(Calendar.SECOND, 0)
        calendarNow.set(Calendar.MILLISECOND, 0)
        return calendarNow.timeInMillis
    }

    fun getEndOfDay(): Long{
        return getEndOfDay(Calendar.getInstance().timeInMillis)
    }

    fun getEndOfDay(timeInMillis: Long): Long{
        val calendarNow = Calendar.getInstance().clone() as Calendar
        calendarNow.timeInMillis = timeInMillis
        val date = calendarNow.get(Calendar.DATE)
        calendarNow.set(Calendar.DATE, date+1)
        calendarNow.set(Calendar.HOUR_OF_DAY, 0)
        calendarNow.set(Calendar.MINUTE, 0)
        calendarNow.set(Calendar.SECOND, 0)
        calendarNow.set(Calendar.MILLISECOND, 0)
        return calendarNow.timeInMillis
    }
}