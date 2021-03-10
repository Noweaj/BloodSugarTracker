package com.noweaj.android.bloodsugartracker.util.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.util.chart.*
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.util.*

private val TAG = "DataAccessStrategy"

fun performInitChartOperation(
    databaseQuery: () -> List<ChartEntity>,
    insertSampleChart: suspend (List<ChartEntity>) -> Unit
): LiveData<Resource<Nothing>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { it }
        if(source.isEmpty()){
            // add chart into db and update ChartParams
            val chartList = mutableListOf<ChartEntity>()
            chartList.add(
                ChartEntity(
                    id = 1,
                    title = "Last 24 hours",
                    description = "Overview of past 24 hours",
                    timeframe = 24,
                    option = JsonHelper.JsonObjectHelper()
                        .putOption("isFixedTimeframe", false)
                        .putOption("fixedTimeFrame_from", 0)
                        .putOption("fixedTimeFrame_to", 0)
                        .putOption("eventFilter", JsonHelper.JsonObjectHelper()
                            .putOption("event", "all")
                            .putOption("minValue", 0)
                            .putOption("maxValue", -1)
                            .getObject()
                        )
                        .getObject().toString()
                )
            )
            
            chartList.add(
                ChartEntity(
                    id = 2,
                    title = "Last 7 days",
                    description = "Overview of past 7 days",
                    timeframe = 168,
                    option = JsonHelper.JsonObjectHelper()
                        .putOption("isFixedTimeframe", false)
                        .putOption("fixedTimeFrame_from", 0)
                        .putOption("fixedTimeFrame_to", 0)
                        .putOption("eventFilter", JsonHelper.JsonObjectHelper()
                            .putOption("event", "all")
                            .putOption("minValue", 0)
                            .putOption("maxValue", -1)
                            .getObject()
                        )
                        .getObject().toString()
                )
            )
            
            insertSampleChart.invoke(
                chartList
            )
            
//            insertSampleChart.invoke(
//                ChartParams.addChart(
//                    ChartEntity(
//                        id = 0,
//                        title = "Last 24 hours",
//                        description = "Overview of past 24 hours",
//                        timeframe = 24,
//                        option = JsonHelper.JsonObjectHelper()
//                            .putOption("isFixedTimeframe", false)
//                            .putOption("fixedTimeFrame_from", 0)
//                            .putOption("fixedTimeFrame_to", 0)
//                            .putOption("eventFilter", JsonHelper.JsonObjectHelper()
//                                .putOption("event", "all")
//                                .putOption("minValue", 0)
//                                .putOption("maxValue", -1)
//                                .getObject()
//                            )
//                            .getObject().toString()
//                    )
//                )
//            )
            // emit result as success
            emit(Resource.success(null))
        } else {
            Log.d(TAG, "chart: ${source[0].id} ${source[0].title} ${source[0].timeframe} ${source[0].option}")
            // emit result as error
            emit(Resource.error("chart list is not empty", null))
        }
    }

fun performLocalGetAllChartSpecOperation(
    chartDatabaseQuery: () -> List<ChartEntity>,
    eventDatabaseQuery: (Long, Long) -> List<EventEntity>
): LiveData<Resource<List<ChartSpec>>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val chartList = chartDatabaseQuery.invoke().map { it }
        if(chartList.isEmpty())
            emit(Resource.error("chart list is empty. please restart the application"))
        val eventList = mutableListOf<List<EventEntity>>()
        for(i in chartList.indices){
            val chartOption = JSONObject(chartList[i].option)
            val isFixedTimeframe = chartOption.getBoolean("isFixedTimeframe")
            var from: Long = 0
            var to: Long = 0
            if(isFixedTimeframe){
                from = chartOption.getLong("fixedTimeframe_from")
                to = chartOption.getLong("fixedTimeframe_to")
            } else {
                from = CalendarUtil.getStartOfDay(Calendar.getInstance().timeInMillis - (chartList[i].timeframe*3600000))
                to = CalendarUtil.getEndOfDay()
            }
            val eventEntities = eventDatabaseQuery.invoke(from, to)
            eventList.add(eventEntities)
        }
        if(eventList.isNotEmpty()){
            val chartSpecs = mutableListOf<ChartSpec>()
            for(i in chartList.indices){
                chartSpecs.add(
                    ChartSpec(
                        chartList[i],
                        eventList[i]
                    )
                )
            }
            emit(Resource.success(chartSpecs.toList()))
        } else {
            emit(Resource.error("event list is empty", null))
        }
    }

fun performLocalGetAllChartEntitiesOperation(
    chartDatabaseQuery: suspend() -> List<ChartEntity>
): LiveData<Resource<List<ChartEntity>>> = 
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val queryResult = chartDatabaseQuery.invoke()
        if(queryResult.isNotEmpty())
            emit(Resource.success(queryResult))
        else
            emit(Resource.error("list empty", null))
//        if(getResult.status == Resource.Status.SUCCESS){
//            emit(Resource.success(getResult.data!!))
//        } else {
//            emit(Resource.error(getResult.message!!, null))
//        }
    }

fun performLocalInsertChartOperation(
    chartDatabaseQuery: suspend () -> Long
): LiveData<Resource<Long>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val queryResult = chartDatabaseQuery.invoke()
        if(queryResult > 0.toLong()){
            emit(Resource.success(queryResult))
        } else {
            emit(Resource.error("adding row failed", null))
        }
    }

fun performLocalDeleteChartOperation(
    chartDatabaseQuery: suspend() -> Int
): LiveData<Resource<Int>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val queryResult = chartDatabaseQuery.invoke()
        if(queryResult == 1){
            emit(Resource.success(queryResult))
        } else {
            emit(Resource.error("deleted row is not 1", null))
        }
    }















fun performLocalGetAllChartOperation(
    chartDatabaseQuery: suspend() -> Resource<List<ChartEntity>>
): LiveData<Resource<List<ChartEntity>>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val getResult = chartDatabaseQuery.invoke()
        if(getResult.status == Resource.Status.SUCCESS){
            val resultData = getResult.data!!
            if(resultData.isEmpty())
                emit(Resource.error("empty list", null))
            else
                emit(Resource.success(getResult.data!!))
        } else {
            emit(Resource.error(getResult.message!!, null))
        }
    }





fun performLocalSingleInsertOperation(
    method: suspend () -> Resource<Long>
): LiveData<Resource<Long>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val dbInsertResult = method.invoke()
        if(dbInsertResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(dbInsertResult.data))
        } else {
            emit(Resource.error(dbInsertResult.message!!, null))
        }
    }

fun performLocalMultipleInsertOperation(
    method: suspend () -> Resource<List<Long>>
): LiveData<Resource<List<Long>>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val dbInsertMultipleResult = method.invoke()
        if(dbInsertMultipleResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(dbInsertMultipleResult.data))
        } else {
            emit(Resource.error(dbInsertMultipleResult.message!!, null))
        }
    }

fun performLocalDeleteOperation(
    method: suspend () -> Resource<Int>
): LiveData<Resource<Int>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val dbDeleteResult = method.invoke()
        if(dbDeleteResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(dbDeleteResult.data))
        } else {
            emit(Resource.error(dbDeleteResult.message!!, null))
        }
    }

fun performLocalGetEventOperation(
    method: suspend() -> Resource<List<EventEntity>>
): LiveData<Resource<List<EventEntity>>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val getResult = method.invoke()
        if(getResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(getResult.data!!))
        } else {
            emit(Resource.error(getResult.message!!, null))
        }
    }



fun performLocalGetEventByChartOperation(
    method: suspend() -> Resource<ChartSpec>
): LiveData<Resource<ChartSpec>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val getResult = method.invoke()
        if(getResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(getResult.data!!))
        } else {
            emit(Resource.error(getResult.message!!, null))
        }
    }