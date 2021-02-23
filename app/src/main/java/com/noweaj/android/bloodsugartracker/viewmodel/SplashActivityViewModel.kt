package com.noweaj.android.bloodsugartracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.util.chart.ChartParams
import com.noweaj.android.bloodsugartracker.util.data.Resource

class SplashActivityViewModel(
    private val chartRepository: ChartRepository
): ViewModel() {
    
    val chartEntities: LiveData<Resource<List<ChartEntity>>> = chartRepository.getAllChart()
    var addSampleChart: LiveData<Resource<Long>> = 
    
//    private val _sampleChartAdded = MutableLiveData<Resource<Long>>()
//    val sampleChartAdded: LiveData<Resource<Long>>
//        get() = _sampleChartAdded
    
    fun setChartParams(chartEntities: List<ChartEntity>){
        ChartParams.updateChartParams(chartEntities)
    }
    
    fun addSampleChart(){
        
    }
}