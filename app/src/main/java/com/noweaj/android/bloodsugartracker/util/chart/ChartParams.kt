package com.noweaj.android.bloodsugartracker.util.chart

import android.os.health.TimerStat
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object ChartParams {
    val CHART_PARAM = mutableListOf<ChartEntity>()
    
    fun updateChartParams(chartEntities: List<ChartEntity>){
        for(i in chartEntities.indices){
            
        }
    }
    
    fun addChart(chartEntity: ChartEntity): ChartEntity{
        CHART_PARAM.add(chartEntity)
        return chartEntity
    }
    
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