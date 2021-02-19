package com.noweaj.android.bloodsugartracker.data.local

import androidx.lifecycle.LiveData
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.util.data.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChartLocalDataSource(
    private val dao: ChartDao
) {
    fun getEntityCount(): LiveData<Int> = dao.getDataCount()
    
    suspend fun insertEntity(chartEntity: ChartEntity?): Resource<Long> {
        var id: Long = 0
        chartEntity?: return Resource(
            Resource.Status.ERROR,
            -1,
            "null chartEntity"
        )
        val insertJob = CoroutineScope(Dispatchers.IO).launch { 
            id = dao.insertEntity(chartEntity)
        }
        insertJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            id,
            null
        )
    }
    
    suspend fun insertEntities(chartEntities: List<ChartEntity>): Resource<List<Long>> {
        val list: MutableList<Long> = mutableListOf()
        val insertJob = CoroutineScope(Dispatchers.IO).launch { 
            list.addAll(dao.insertEntities(chartEntities).toMutableList())
        }
        insertJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            list.toList(),
            null
        )
    }
    
    suspend fun deleteEntity(chartEntity: ChartEntity): Resource<Int> {
        var id = 0
        val deleteJob = CoroutineScope(Dispatchers.IO).launch { 
            id = dao.deleteEntity(chartEntity)
        }
        deleteJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            id,
            null
        )
    }
    
    suspend fun getAllEntities(): Resource<List<ChartEntity>> {
        val chartEntities = mutableListOf<ChartEntity>()
        val getJob = CoroutineScope(Dispatchers.IO).launch { 
            chartEntities.addAll(dao.getAllEntities())
        }
        getJob.join()
        return Resource(
            Resource.Status.SUCCESS,
            chartEntities,
            null
        )
    }
}