package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.navigator.EventNavigator
import java.lang.ref.WeakReference

class EventAddValueNoteViewModel(
    private val entity: EventEntity
): ViewModel() {

    private val TAG = EventAddValueNoteViewModel::class.java.simpleName

    private var navigator: WeakReference<EventNavigator>? = null

    val glucose = ObservableField<String>()
    val note = ObservableField<String>()

    fun setNavigator(navigator: EventNavigator){
        this.navigator = WeakReference(navigator)
    }

    fun onSaveButtonClicked(){
        Log.d(TAG, "glucose: ${glucose.get()}, note: ${note.get()}")
        navigator?.let {
            it.get()!!.proceed(EventEntity(
                timestamp = entity.timestamp,
                event = entity.event,
                value = Integer.parseInt(glucose.get()),
                note = note.get()
            ))
        }
    }
}