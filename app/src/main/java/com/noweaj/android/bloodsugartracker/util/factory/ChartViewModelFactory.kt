package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.viewmodel.ChartViewModel

class ChartViewModelFactory(
    private val chartRepository: ChartRepository,
    private val eventRepository: EventRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChartViewModel(chartRepository, eventRepository) as T
    }
}