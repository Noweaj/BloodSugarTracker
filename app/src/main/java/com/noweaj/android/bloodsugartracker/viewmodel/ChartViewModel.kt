package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.util.chart.ChartData
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.util.oneDayInTimeMillis
import java.util.*

class ChartViewModel(
    private val chartRepository: ChartRepository,
    private val eventRepository: EventRepository
) : ViewModel() {
    private val TAG = ChartViewModel::class.java.simpleName
    
    var chartEntityCount: LiveData<Int>
        = chartRepository.entityCount
    var sampleChartAdded: LiveData<Resource<Long>>
        = chartRepository.insertSingleChart(null)
    var chartEntities: LiveData<Resource<List<ChartEntity>>>
        = chartRepository.getAllEntities()
    var chartData: LiveData<Resource<ChartData>>
        = eventRepository.getEntitiesByChartList(null)

    fun addSampleChart(){
        Log.d(TAG, "addSampleChart")
        // add sample chart since no chart entity is available
        // basic 1 day chart
        val timeNow = Calendar.getInstance().timeInMillis
        val result = chartRepository.insertSingleChart(
            ChartEntity(
                title = "Last 24 hours",
                description = "Overview of past 24 hours",
                from = timeNow - oneDayInTimeMillis,
                to = timeNow,
                option = ""
            )
        )
        updateChart()
    }
    
    fun updateChart(){
        chartEntities = chartRepository.getAllEntities()
    }
    
    fun getEventEntities(chartEntities: List<ChartEntity>){
        chartData = eventRepository.getEntitiesByChartList(chartEntities)
    }
}