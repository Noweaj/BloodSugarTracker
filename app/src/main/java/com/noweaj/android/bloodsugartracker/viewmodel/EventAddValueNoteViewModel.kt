package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.repository.EventRepository
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import java.lang.ref.WeakReference

class EventAddValueNoteViewModel(
    private val repository: GlucoseRepository
): ViewModel() {

    private val TAG = EventAddValueNoteViewModel::class.java.simpleName

    private var entity: WeakReference<EventEntity>? = null

    val glucose = ObservableField<String>()
    val note = ObservableField<String>()

    fun setEventEntity(eventEntity: EventEntity){
        this.entity = WeakReference(eventEntity)
    }

//    private val _insertEvent = MutableLiveData<EventEntity>()
//    val insertEvent = _insertEvent.switchMap {
//        repository.insertSingleEvent(it)
//    }

    fun onSaveButtonClicked(){
        Log.d(TAG, "glucose: ${glucose.get()}, note: ${note.get()}")
//        _insertEvent.postValue(EventEntity(
//            timestamp = entity!!.get()!!.timestamp,
//            event = entity!!.get()!!.event,
//            value = Integer.parseInt(glucose.get()),
//            note = note.get()
//        ))
    }
}