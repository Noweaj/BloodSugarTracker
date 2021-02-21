package com.noweaj.android.bloodsugartracker.util.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.util.chart.ChartData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

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

fun performLocalGetChartOperation(
    method: suspend() -> Resource<List<ChartEntity>>
): LiveData<Resource<List<ChartEntity>>> = 
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
    method: suspend() -> Resource<ChartData>
): LiveData<Resource<ChartData>> =
    liveData(Dispatchers.IO) { 
        emit(Resource.loading())
        val getResult = method.invoke()
        if(getResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(getResult.data!!))
        } else {
            emit(Resource.error(getResult.message!!, null))
        }
    }