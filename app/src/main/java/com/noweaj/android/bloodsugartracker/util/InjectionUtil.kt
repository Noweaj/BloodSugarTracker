package com.noweaj.android.bloodsugartracker.util

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.local.ChartLocalDataSource
import com.noweaj.android.bloodsugartracker.data.local.EventDao
import com.noweaj.android.bloodsugartracker.data.local.EventLocalDataSource
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.util.factory.ChartViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddTimeEventViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddValueNoteViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.SplashActivityViewModelFactory

object InjectionUtil {
    fun provideEventRepository(dao: EventDao): EventRepository{
        return EventRepository(
            // remoteDataSource,
            localDataSource = EventLocalDataSource(dao)
        )
    }
    
    fun provideChartRepository(dao: ChartDao): ChartRepository{
        return ChartRepository(
            localDataSource = ChartLocalDataSource(dao)
        )
    }
    
    fun provideSplashActivityViewModelFactory(
        chartRepository: ChartRepository
    ): SplashActivityViewModelFactory {
        return SplashActivityViewModelFactory(
            chartRepository
        )
    }
    
    fun provideChartViewModelFactory(
        chartRepository: ChartRepository,
        eventRepository: EventRepository
    ): ChartViewModelFactory {
        return ChartViewModelFactory(
            chartRepository = chartRepository,
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