package com.noweaj.android.bloodsugartracker.util.chart

import org.json.JSONArray
import org.json.JSONObject

object JsonHelper {
    class JsonArrayHelper {
        private val jsonArrayHelper = JSONArray()

        fun addArray(arr: Array<JSONObject>): JsonArrayHelper{
            for(i in arr.indices)
                jsonArrayHelper.put(arr[i])
            return this
        }

        fun getArray() = jsonArrayHelper
    }

    class JsonObjectHelper {
        private val jsonObjectHelper = JSONObject()

        fun <T> putOption(key: String, value: T): JsonObjectHelper{
            jsonObjectHelper.put(key, value)
            return this
        }

        fun getObject() = jsonObjectHelper
    }
}