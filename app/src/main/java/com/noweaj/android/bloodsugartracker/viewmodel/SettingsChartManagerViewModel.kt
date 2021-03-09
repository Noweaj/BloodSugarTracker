package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import com.noweaj.android.bloodsugartracker.util.chart.ChartSpec
import com.noweaj.android.bloodsugartracker.util.data.Resource
import java.lang.ref.WeakReference

class SettingsChartManagerViewModel(
    private val repository: GlucoseRepository
): ViewModel() {
    private val TAG = SettingsChartManagerViewModel::class.java.simpleName
    
    private val _updateChart = MutableLiveData<Unit>()
    private val _chartList = _updateChart.switchMap { 
        repository.getAllChartEntities()
    }
    val chartList: LiveData<Resource<List<ChartEntity>>> = _chartList
    
    private var navigator: WeakReference<SettingsNavigator>? = null
    
    fun setNavigator(navigator: SettingsNavigator){
        this.navigator = WeakReference(navigator)
    }
    
    fun updateChartList(){
        Log.d(TAG, "updateChart")
        _updateChart.postValue(Unit)
    }
    
    fun onButtonClicked(v: View){
        when(v.id){
            R.id.iv_settings_chartmanager_addchart -> {
                Log.d(TAG, "addChart clicked")
            }
        }
    }
}