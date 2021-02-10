package com.noweaj.android.bloodsugartracker.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import kotlinx.coroutines.Dispatchers

fun performLocalInsertDeleteOperation(
    method: suspend() -> Resource<Unit>
): LiveData<Resource<Unit>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val dbInsertResult = method.invoke()
        if(dbInsertResult.status == Resource.Status.SUCCESS){
            emit(Resource.success(null))
        } else {
            emit(Resource.error(dbInsertResult.message!!, null))
        }
    }

fun performLocalGetOperation(
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