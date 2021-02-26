package com.noweaj.android.bloodsugartracker.util

import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.local.ChartDao
import com.noweaj.android.bloodsugartracker.data.local.ChartLocalDataSource
import com.noweaj.android.bloodsugartracker.data.local.EventDao
import com.noweaj.android.bloodsugartracker.data.local.EventLocalDataSource
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import com.noweaj.android.bloodsugartracker.util.factory.ChartViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddTimeEventViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.EventAddValueNoteViewModelFactory
import com.noweaj.android.bloodsugartracker.util.factory.SplashActivityViewModelFactory

object InjectionUtil {
    fun provideGlucoseRepository(
        chartDao: ChartDao,
        eventDao: EventDao
    ): GlucoseRepository {
        return GlucoseRepository(
            localChartDataSource = chartDao,
            localEventDataSource = eventDao
        )
    }
    
    fun provideEventRepository(dao: EventDao): EventRepository{
        return EventRepository(
            // remoteDataSource,
            localDataSource = EventLocalDataSource(dao)
        )
    }
    
    fun provideChartRepository(dao: ChartDao): ChartRepository{
        return ChartRepository(
            localDataSource = dao
        )
    }
    
    fun provideSplashActivityViewModelFactory(
        glucoseRepository: GlucoseRepository
    ): SplashActivityViewModelFactory {
        return SplashActivityViewModelFactory(
            repository = glucoseRepository
        )
    }
    
    fun provideChartViewModelFactory(
        glucoseRepository: GlucoseRepository
    ): ChartViewModelFactory {
        return ChartViewModelFactory(
            repository = glucoseRepository
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
        glucoseRepository: GlucoseRepository
    ): EventAddValueNoteViewModelFactory {
        return EventAddValueNoteViewModelFactory(
           repository = glucoseRepository
        )
    }
    
}