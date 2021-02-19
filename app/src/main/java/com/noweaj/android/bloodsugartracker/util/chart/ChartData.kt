package com.noweaj.android.bloodsugartracker.util.chart

import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity

data class ChartData(
    val chartEntities: List<ChartEntity>,
    val eventEntitiesPerChart: List<List<EventEntity>>
)