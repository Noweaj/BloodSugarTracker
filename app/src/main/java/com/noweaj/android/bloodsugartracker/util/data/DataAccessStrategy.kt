package com.noweaj.android.bloodsugartracker.util.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.util.chart.*
import kotlinx.coroutines.Dispatchers

fun performInitChartOperation(
    databaseQuery: () -> List<ChartEntity>,
    insertSampleChart: suspend (ChartEntity) -> Unit
): LiveData<Resource<Nothing>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val source = databaseQuery.invoke()
        if(source.isEmpty()){
            // add chart into db and update ChartParams
            insertSampleChart.invoke(
                ChartParams.addChart(
                    ChartEntity(
                        id = 0,
                        title = "Last 24 hours",
                        description = "Overview of past 24 hours",
                        timeframe = 24,
                        option = JsonHelper.JsonObjectHelper()
                            .putOption("isFixedTimeframe", false)
                            .putOption("eventFilter", JsonHelper.JsonObjectHelper()
                                .putOption("event", "all")
                            )
                            .getObject().toString()
                    )
                )
            )
            // emit result as success
            emit(Resource.success(null))
        } else {
            // update ChartParams
            ChartParams.setChartParams(source)
            // emit result as error
            emit(Resource.error("chart list is not empty", null))
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

fun performLocalGetAllChartOperation(
    method: suspend() -> Resource<List<ChartEntity>>
): LiveData<Resource<List<ChartEntity>>> = 
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val getResult = method.invoke()
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