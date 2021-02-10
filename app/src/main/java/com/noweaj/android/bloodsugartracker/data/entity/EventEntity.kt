package com.noweaj.android.bloodsugartracker.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "eventENTITY")
@Parcelize
data class EventEntity(
        @PrimaryKey val timestamp: Long,
        val event: String,
        var value: Int = 0,
        var note: String? = null
): Parcelable