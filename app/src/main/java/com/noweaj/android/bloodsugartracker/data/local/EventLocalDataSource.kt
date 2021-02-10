package com.noweaj.android.bloodsugartracker.data.local

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EventLocalDataSource(
    private val dao: EventDao
) {
    suspend fun insertEntity(eventEntity: EventEntity): Resource<Unit> {
        val insertJob = CoroutineScope(Dispatchers.IO).launch {
            dao.insertEntity(eventEntity)
        }
        insertJob.join()
        return Resource(Resource.Status.SUCCESS, null, null)
    }

    suspend fun insertEntities(eventEntities: List<EventEntity>): Resource<Unit>{
        val insertJob = CoroutineScope(Dispatchers.IO).launch {
            dao.insertEntities(eventEntities)
        }
        insertJob.join()
        return Resource(Resource.Status.SUCCESS, null, null)
    }

    suspend fun deleteEntity(eventEntity: EventEntity): Resource<Unit> {
        val deleteJob = CoroutineScope(Dispatchers.IO).launch {
            dao.deleteEntity(eventEntity)
        }
        deleteJob.join()
        return Resource(Resource.Status.SUCCESS, null, null)
    }

    suspend fun getEntitiesBetweenDates(startDate: Long, endDate: Long): Resource<List<EventEntity>>{
        val eventEntities = mutableListOf<EventEntity>()
        val getJob = CoroutineScope(Dispatchers.IO).launch {
            eventEntities.addAll(dao.getEntitiesBetweenDates(startDate, endDate))
        }
        getJob.join()
        return Resource(Resource.Status.SUCCESS, eventEntities, null)
    }
}