package com.noweaj.android.bloodsugartracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chartENTITY")
data class ChartEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,   
        var title: String,
        var description: String,
        var timeframe: Int, // hours
        var option: String
)