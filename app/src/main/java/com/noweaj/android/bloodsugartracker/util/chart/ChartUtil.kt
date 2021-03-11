package com.noweaj.android.bloodsugartracker.util.chart

import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
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
        xAxis.setLabelCount(24, false)

        xAxis.axisMaximum = 24f
        xAxis.axisMinimum = 0f

        val yAxis: YAxis = chart.axisLeft
        chart.axisRight.isEnabled = false

        yAxis.axisMaximum = 250f
        yAxis.axisMinimum = 40f

        // set data

        val data = arrayListOf(
            Entry(7F, 100F),
            Entry(9F, 90F),
            Entry(12F, 150F),
            Entry(14F, 125F),
            Entry(16F, 100F),
            Entry(19F, 130F),
            Entry(20F, 100F),
            Entry(22F, 90F)
        )

        val set1 = LineDataSet(data, "dataset1")

        set1.color = Color.BLACK
        set1.setCircleColor(Color.BLUE)

        set1.lineWidth = 1f
        set1.circleRadius = 3f

        set1.valueTextSize = 12f

        val lineData = LineData()
        lineData.addDataSet(set1)

        val combinedData = CombinedData()
        combinedData.setData(lineData)

        chart.data = combinedData
    }
    
}