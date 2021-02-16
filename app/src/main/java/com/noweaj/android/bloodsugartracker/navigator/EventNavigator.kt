package com.noweaj.android.bloodsugartracker.navigator

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity

interface EventNavigator {
    fun proceed(eventEntity: EventEntity)
}