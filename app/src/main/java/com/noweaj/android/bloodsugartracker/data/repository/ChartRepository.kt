package com.noweaj.android.bloodsugartracker.data.repository

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.local.ChartLocalDataSource
import com.noweaj.android.bloodsugartracker.util.data.*

class ChartRepository(
    private val localDataSource: ChartDao
){

    /**
     * local
     */
    fun initChartInformation() =
        performInitChartOperation(
            databaseQuery = { localDataSource.getAllEntities() },
            insertSampleChart = { localDataSource.insertEntity(it) }
        )
//    
//    fun insertSampleChartIfNeeded(chartEntity: ChartEntity) = 
//        performLocalSingleInsertOperation(
//            method = { localDataSource.insertSampleEntity(chartEntity) }
//        )
//    
//    fun insertSingleChart(chartEntity: ChartEntity) = 
//        performLocalSingleInsertOperation(
//            method = { localDataSource.insertEntity(chartEntity) }
//        )
//    
//    fun insertMultipleChart(chartEntities: List<ChartEntity>) =
//        performLocalMultipleInsertOperation(
//            method = { localDataSource.insertEntities(chartEntities) }
//        )
//    
//    fun deleteChart(chartEntity: ChartEntity) = 
//        performLocalDeleteOperation(
//            method = { localDataSource.deleteEntity(chartEntity) }
//        )
//    
//    fun getAllChart() = 
//        performLocalGetAllChartOperation(
//            method = { localDataSource.getAllEntities() }
//        )
//    
    
}