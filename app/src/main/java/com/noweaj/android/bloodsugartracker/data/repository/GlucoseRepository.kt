package com.noweaj.android.bloodsugartracker.data.repository

import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.local.EventDao
import com.noweaj.android.bloodsugartracker.util.data.performInitChartOperation
import com.noweaj.android.bloodsugartracker.util.data.performUpdateChartOperation

class GlucoseRepository (
    private val localChartDataSource: ChartDao,
    private val localEventDataSource: EventDao
){
    fun initChartInformation() = 
        performInitChartOperation(
            databaseQuery = { localChartDataSource.getAllEntities() },
            insertSampleChart = { localChartDataSource.insertEntity(it) }
        )
    
    fun updateChartInformation() =
        performUpdateChartOperation(
            chartDatabaseQuery = { localChartDataSource.getAllEntities() },
            eventDatabaseQuery = { from, to ->
                localEventDataSource.getEntitiesBetweenDates(from, to) }
        )
    
    
}