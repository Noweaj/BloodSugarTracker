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
    private val CHART_PARAM = mutableListOf<ChartEntity>()
    
    fun getChartParams(): List<ChartEntity>{
        return CHART_PARAM
    }
    
    fun setChartParams(chartEntities: List<ChartEntity>){
        CHART_PARAM.addAll(chartEntities)
    }
    
    fun addChart(chartEntity: ChartEntity): ChartEntity{
        CHART_PARAM.add(chartEntity)
        return chartEntity
    }
}