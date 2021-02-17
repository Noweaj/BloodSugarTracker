package com.noweaj.android.bloodsugartracker.data.repository

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.local.EventLocalDataSource
import com.noweaj.android.bloodsugartracker.util.data.performLocalDeleteOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalGetOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalMultipleInsertOperation
import com.noweaj.android.bloodsugartracker.util.data.performLocalSingleInsertOperation

class EventRepository(
    private val localDataSource:EventLocalDataSource
) {

    /**
     * local
     */
    fun insertSingleEvent(eventEntity: EventEntity) =
        performLocalSingleInsertOperation(
            method = { localDataSource.insertEntity(eventEntity) }
        )
    fun insertMultipleEvent(eventEntities: List<EventEntity>) =
        performLocalMultipleInsertOperation(
            method = { localDataSource.insertEntities(eventEntities) }
        )
    fun deleteEvent(eventEntities: List<EventEntity>) =
        performLocalDeleteOperation(
            method = { localDataSource.deleteEntities(eventEntities) }
        )
    fun getEntitiesBetweenDates(startDate: Long, endDate: Long) =
        performLocalGetOperation(
            method = { localDataSource.getEntitiesBetweenDates(startDate, endDate) }
        )
}