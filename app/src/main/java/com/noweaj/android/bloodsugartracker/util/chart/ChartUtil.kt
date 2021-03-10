package com.noweaj.android.bloodsugartracker.util.chart

import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import org.json.JSONObject

object ChartUtil {
    
    fun generateChartOption(): JSONObject {
        return JSONObject()
    }
    
    fun putOption(obj: JSONObject, key: String, value: String): JSONObject{
        obj.put(key, value)
        return obj
    }
    
    // test
    fun setLineChart(chart: CombinedChart){
        chart.setBackgroundColor(Color.WHITE)
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)

        //chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)

        chart.isDragEnabled = true
        chart.setScaleEnabled(true)

        chart.setPinchZoom(true)

        val xAxis: XAxis = chart.xAxis
        xAxis.enableGridDashedLine(
            10f,
            10f,
            0f
        )

        val yAxis: YAxis = chart.axisLeft
        yAxis.enableGridDashedLine(
            10f,
            10f,
            0f
        )
        
        yAxis.mAxisMaximum = 200f
        yAxis.axisMinimum = 0f
        
        // set data
        
        val data = arrayListOf<Long>(
            100, 150, 125, 100, 90, 75
        )
        
        

    }
    
}