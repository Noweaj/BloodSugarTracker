package com.noweaj.android.bloodsugartracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chartENTITY")
data class ChartEntity(
        @PrimaryKey var title: String,
        var description: String,
        var from: Long,
        var to: Long,
        var option: String
)