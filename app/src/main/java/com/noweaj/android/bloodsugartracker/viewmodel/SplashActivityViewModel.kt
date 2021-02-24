package com.noweaj.android.bloodsugartracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.util.chart.ChartParams
import com.noweaj.android.bloodsugartracker.util.data.Resource

class SplashActivityViewModel(
    chartRepository: ChartRepository
): ViewModel() {
    
    val initChart = chartRepository.initChartInformation()
}