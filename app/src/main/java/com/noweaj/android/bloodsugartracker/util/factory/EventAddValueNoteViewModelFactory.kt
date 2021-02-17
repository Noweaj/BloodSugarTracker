package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddValueNoteViewModel

class EventAddValueNoteViewModelFactory(
    private val eventRepository: EventRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventAddValueNoteViewModel(eventRepository) as T
    }
}