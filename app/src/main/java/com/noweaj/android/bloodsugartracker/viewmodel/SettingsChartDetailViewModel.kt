package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import com.noweaj.android.bloodsugartracker.util.data.Resource
import java.lang.ref.WeakReference

class SettingsChartDetailViewModel(
    private val repository: GlucoseRepository
): ViewModel() {
    private val TAG = SettingsChartDetailViewModel::class.java.simpleName
    
    private var navigator: WeakReference<SettingsNavigator>? = null
    
    fun setNavigator(navigator: SettingsNavigator){
        this.navigator = WeakReference(navigator)
    }
    
    var chartId: Long = 0
    var chartTitle = ObservableField("")
    var chartDescription = ObservableField("")
    var chartTimeframe = ObservableField("")
    var chartOption = ObservableField("")
    
    fun setChartEntity(chartEntity: ChartEntity){
        this.chartId = chartEntity.id
        this.chartTitle = ObservableField(chartEntity.title)
        this.chartDescription = ObservableField(chartEntity.description)
        this.chartTimeframe = ObservableField(chartEntity.timeframe.toString())
        this.chartOption = ObservableField(chartEntity.option)
    }
    
    private val _saveChart = MutableLiveData<ChartEntity>()
    private val _chartSaved = _saveChart.switchMap {
        repository.insertChartEntity(it)
    }
    val chartSaved: LiveData<Resource<Long>> = _chartSaved
    
    fun onSaveButtonClicked(){
        Log.d(TAG, "onSaveButtonClicked")
        // save chartentity
        _saveChart.postValue(
            ChartEntity(
                chartId,
                chartTitle.get()!!,
                chartDescription.get()!!,
                chartTimeframe.get()!!.toInt(),
                chartOption.get()!!
            )
        )
    }
}