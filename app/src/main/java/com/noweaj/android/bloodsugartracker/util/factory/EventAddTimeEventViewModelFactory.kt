package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddTimeEventViewModel

class EventAddTimeEventViewModelFactory(
    private val eventEntity: EventEntity
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventAddTimeEventViewModel(eventEntity) as T
    }
}