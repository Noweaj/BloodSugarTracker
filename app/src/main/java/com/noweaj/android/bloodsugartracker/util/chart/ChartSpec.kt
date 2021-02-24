package com.noweaj.android.bloodsugartracker.util.chart

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity

data class ChartSpec(
    val chartEntity: ChartEntity,
    val eventEntities: List<EventEntity>
)