package com.noweaj.android.bloodsugartracker.data.local

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.util.chart.ChartData
import com.noweaj.android.bloodsugartracker.util.data.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventLocalDataSource(
    private val dao: EventDao
) {
    suspend fun insertEntity(eventEntity: EventEntity): Resource<Long> {
        var id: Long = 0
        val insertJob = CoroutineScope(Dispatchers.IO).launch {
            id = dao.insertEntity(eventEntity)
        }
        insertJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            id,
            null
        )
    }

    suspend fun insertEntities(eventEntities: List<EventEntity>): Resource<List<Long>> {
        val list: MutableList<Long> = mutableListOf()
        val insertJob = CoroutineScope(Dispatchers.IO).launch {
            list.addAll(dao.insertEntities(eventEntities).toMutableList())
        }
        insertJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            list.toList(),
            null
        )
    }

    suspend fun deleteEntities(eventEntries: List<EventEntity>): Resource<Int> {
        var id = 0
        val deleteJob = CoroutineScope(Dispatchers.IO).launch {
            id = dao.deleteEntities(eventEntries)
        }
        deleteJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            id,
            null
        )
    }

    suspend fun getEntitiesBetweenDates(startDate: Long, endDate: Long): Resource<List<EventEntity>> {
        val eventEntities = mutableListOf<EventEntity>()
        val getJob = CoroutineScope(Dispatchers.IO).launch {
            eventEntities.addAll(dao.getEntitiesBetweenDates(startDate, endDate))
        }
        getJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            eventEntities,
            null
        )
    }
    
    suspend fun getEntitiesBetweenDatesByChart(chartEntities: List<ChartEntity>?): Resource<ChartData> {
        chartEntities?: return Resource(
            Resource.Status.ERROR,
            null,
            "chartEntities is null"
        )
        val eventEntities = mutableListOf<List<EventEntity>>()
        for(i in chartEntities.indices){
            val getJob = CoroutineScope(Dispatchers.IO).launch { 
                eventEntities.add(dao.getEntitiesBetweenDates(
                    chartEntities.get(i).from,
                    chartEntities.get(i).to
                ))
            }
            getJob.join()
        }
        return Resource(
            Resource.Status.SUCCESS,
            ChartData(
                chartEntities = chartEntities,
                eventEntitiesPerChart = eventEntities
            ),
            null
        )
    }
}