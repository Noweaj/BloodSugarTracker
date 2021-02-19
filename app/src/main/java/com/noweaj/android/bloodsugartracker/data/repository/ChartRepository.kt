package com.noweaj.android.bloodsugartracker.data.repository

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.local.ChartLocalDataSource
import com.noweaj.android.bloodsugartracker.util.data.*

class ChartRepository(
    private val localDataSource: ChartLocalDataSource
){

    /**
     * local
     */
    val entityCount = localDataSource.getEntityCount()
    
    fun insertSingleChart(chartEntity: ChartEntity?) = 
        performLocalSingleInsertOperation(
            method = { localDataSource.insertEntity(chartEntity) }
        )
    
    fun insertMultipleChart(chartEntities: List<ChartEntity>) =
        performLocalMultipleInsertOperation(
            method = { localDataSource.insertEntities(chartEntities) }
        )
    
    fun deleteChart(chartEntity: ChartEntity) = 
        performLocalDeleteOperation(
            method = { localDataSource.deleteEntity(chartEntity) }
        )
    
    fun getAllEntities() = 
        performLocalGetChartOperation(
            method = { localDataSource.getAllEntities() }
        )
}