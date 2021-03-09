package com.noweaj.android.bloodsugartracker.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "chartENTITY")
@Parcelize
data class ChartEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,   
        var title: String,
        var description: String,
        var timeframe: Int, // hours
        var option: String
): Parcelable