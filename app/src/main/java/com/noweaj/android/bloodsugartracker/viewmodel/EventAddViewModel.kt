package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class EventAddViewModel: ViewModel() {

    private val TAG = EventAddViewModel::class.java.simpleName

    val timeStamp = ObservableField<String>()
    val event = ObservableField<String>()
    val glucose = ObservableField<String>()
    val note = ObservableField<String>()

    fun onSaveButtonClicked(){
        Log.d(TAG, "values: ${timeStamp.get()}, ${event.get()}, ${glucose.get()}, ${note.get()}")
    }
}