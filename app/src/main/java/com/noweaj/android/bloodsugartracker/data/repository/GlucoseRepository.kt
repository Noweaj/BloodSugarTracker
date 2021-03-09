package com.noweaj.android.bloodsugartracker.data.repository

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.local.EventDao
import com.noweaj.android.bloodsugartracker.util.data.performInitChartOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalDeleteChartOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalGetAllChartEntitiesOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalGetAllChartSpecOperation

class GlucoseRepository (
    private val localChartDataSource: ChartDao,
    private val localEventDataSource: EventDao
){
    fun initChartInformation() = 
        performInitChartOperation(
            databaseQuery = { localChartDataSource.getAllEntities() },
//            insertSampleChart = { localChartDataSource.insertEntity(it) }
            insertSampleChart = { localChartDataSource.insertEntities(it) }
        )
    
    fun getAllChartSpecs() =
        performLocalGetAllChartSpecOperation(
            chartDatabaseQuery = { localChartDataSource.getAllEntities() },
            eventDatabaseQuery = { from, to ->
                localEventDataSource.getEntitiesBetweenDates(from, to) }
        )
    
    fun getAllChartEntities() = 
        performLocalGetAllChartEntitiesOperation(
            chartDatabaseQuery = { localChartDataSource.getAllEntities() }
        )
    
    fun deleteChartEntity(chartEntity: ChartEntity) =
        performLocalDeleteChartOperation(
            chartDatabaseQuery = { localChartDataSource.deleteEntity(chartEntity) }
        )
    
}