package com.noweaj.android.bloodsugartracker.util.chart

import com.noweaj.android.bloodsugartracker.util.SingletonHolder

class ChartSpec_testSingleton(
    private val chartList: List<ChartSpec>
) {
    
    init {
        
    }
    
    companion object: SingletonHolder<ChartSpec_testSingleton, List<ChartSpec>>(::ChartSpec_testSingleton)
    
}

// calling
// val chartSpec = ChartSpec.getInstance(mutableListOf())