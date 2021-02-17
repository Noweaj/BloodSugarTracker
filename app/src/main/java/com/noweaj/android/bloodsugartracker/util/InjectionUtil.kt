package com.noweaj.android.bloodsugartracker.util

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.local.EventDao
import com.noweaj.android.bloodsugartracker.data.local.EventLocalDataSource
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.util.factory.ChartViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddTimeEventViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddValueNoteViewModelFactory

object InjectionUtil {
    fun provideRepository(dao: EventDao): EventRepository{
        return EventRepository(
            // remoteDataSource,
            localDataSource = EventLocalDataSource(dao)
        )
    }
    
    fun provideChartViewModelFactory(
        eventRepository: EventRepository
    ): ChartViewModelFactory {
        return ChartViewModelFactory(
            eventRepository = eventRepository
        )
    }

    fun provideEventAddTimeEventViewModelFactory(
        entity: EventEntity
    ): EventAddTimeEventViewModelFactory {
        return EventAddTimeEventViewModelFactory(
            eventEntity = entity
        )
    }
    
    fun provideEventAddValueNoteViewModelFactory(
        eventRepository: EventRepository
    ): EventAddValueNoteViewModelFactory {
        return EventAddValueNoteViewModelFactory(
            eventRepository = eventRepository
        )
    }
    
}