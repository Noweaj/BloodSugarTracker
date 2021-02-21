package com.noweaj.android.bloodsugartracker.util.chart

import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository

object ChartSpec {
    
    val chartSpec = mutableListOf<ChartData>()
    
    fun initChartSpec(repository: ChartRepository){
        // get chart list
        val charList = repository.getAllEntities()
        
    }
}