package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddValueNoteViewModel

class EventAddValueNoteViewModelFactory(
    private val repository: GlucoseRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventAddValueNoteViewModel(repository) as T
    }
}