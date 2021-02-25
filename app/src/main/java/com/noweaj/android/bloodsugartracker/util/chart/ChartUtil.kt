package com.noweaj.android.bloodsugartracker.util.chart

import org.json.JSONObject

object ChartUtil {
    
    fun generateChartOption(): JSONObject {
        return JSONObject()
    }
    
    fun putOption(obj: JSONObject, key: String, value: String): JSONObject{
        obj.put(key, value)
        return obj
    }
    
}