package com.noweaj.android.bloodsugartracker.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.navigator.EventNavigator
import java.lang.ref.WeakReference

class EventAddTimeEventViewModel(
    entity: EventEntity
): ViewModel() {

    private val TAG = EventAddTimeEventViewModel::class.java.simpleName

    private var navigator: WeakReference<EventNavigator>? = null

    val timestamp = ObservableField(entity.timestamp.toString())
    val event = ObservableField(entity.event)

    fun setNavigator(navigator: EventNavigator){
        this.navigator = WeakReference(navigator)
    }

    fun onSpinnerItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
        Log.d(TAG, "onSpinnerItemSelected: $position ${parent.getItemAtPosition(position)}")
    }

    fun onNextButtonClicked(){
        Log.d(TAG, "timestamp: ${timestamp.get()}, event: ${event.get()}")
        navigator?.let {
            it.get()!!.navigateNext(EventEntity(
                timestamp = timestamp.get()!!.toLong(),
                event = event.get()!!,
                value = 0,
                note = null
            ))
        }
    }
}