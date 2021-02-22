package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.util.chart.ChartSpec
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.util.oneDayInTimeMillis
import java.util.*

class ChartViewModel(
    private val chartRepository: ChartRepository,
    private val eventRepository: EventRepository
) : ViewModel() {
    private val TAG = ChartViewModel::class.java.simpleName

    /**
     * Chart를 저장한 시점으로만 날짜가 고정되는 문제 발견!
     * 날짜를 앱 켤 떄 마다 room에 저장된 range를 기반으로
     * 상수로 만들어 주는 함수가 필요함.
     */
    var sampleChartAdded: LiveData<Resource<Long>>
        = chartRepository.insertSampleChartIfNeeded(
            ChartEntity(
                title = "Last 24 hours",
                description = "Overview of past 24 hours",
                from = (Calendar.getInstance().clone() as Calendar).timeInMillis - oneDayInTimeMillis,
                to = Calendar.getInstance().timeInMillis,
                option = ""
            )
        )
//    private val _sampleChartAdded = MutableLiveData<Resource<Long>>()
//    val sampleChartAdded: LiveData<Resource<Long>>
//        get() = _sampleChartAdded
    var chartEntities: LiveData<Resource<List<ChartEntity>>>
        = chartRepository.getAllEntities()
    var chartSpec: LiveData<Resource<ChartSpec>>
        = eventRepository.getEntitiesByChartList(null)
    
//    private val _chartData = MutableLiveData<Resource<ChartData>>()
//    val chartData: LiveData<Resource<ChartData>>
//        get() = _chartData

//    fun addSampleChart(){
//        Log.d(TAG, "addSampleChart")
//        // add sample chart since no chart entity is available
//        // basic 1 day chart
//        val timeNow = Calendar.getInstance().timeInMillis
//        val result = chartRepository.insertSingleChart(
//            ChartEntity(
//                title = "Last 24 hours",
//                description = "Overview of past 24 hours",
//                from = timeNow - oneDayInTimeMillis,
//                to = timeNow,
//                option = ""
//            )
//        ).value
//        _sampleChartAdded.postValue(result)
//    }
    
    fun checkChartEntity(){
        
    }
    
    fun updateChart(){
        Log.d(TAG, "updateChart")
        chartEntities = chartRepository.getAllEntities()
    }
    
    fun getEventEntities(chartEntities: List<ChartEntity>){
        Log.d(TAG, "getEventEntities")
        chartSpec = eventRepository.getEntitiesByChartList(chartEntities)
//        _chartData.postValue(eventRepository.getEntitiesByChartList(chartEntities).value)
    }
}