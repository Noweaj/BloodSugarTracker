package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddTimeEventBinding
import com.noweaj.android.bloodsugartracker.navigator.EventNavigator
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddTimeEventViewModel
import java.util.*

class EventAddTimeEventFragment: Fragment(), EventNavigator {

    private val TAG = EventAddTimeEventFragment::class.java.simpleName

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddTimeEventBinding.inflate(inflater, container, false)

        var entity = arguments?.get("eventEntity") as EventEntity
        Log.d(TAG, "${entity.event}")
        entity.timestamp = Calendar.getInstance().timeInMillis
        
        val viewModel: EventAddTimeEventViewModel by viewModels { 
            InjectionUtil.provideEventAddTimeEventViewModelFactory(entity)
        }
        
        binding.viewModel = viewModel
        
        val adapter = binding.acsEventEvent.adapter
        val idx = getItemIndex(adapter, entity.event)
        binding.acsEventEvent.setSelection(idx)

        binding.viewModel!!.setNavigator(this)

        return binding.root
    }

    private fun getItemIndex(adapter: SpinnerAdapter, item: String): Int{
        val targetItem = when(item){
            "meal" -> "식사"
            "glucose" -> "혈당"
            "exercise" -> "운동"
            else -> "NA"
        }
        for(i in 0 until adapter.count){
            Log.d(TAG, "${adapter.getItem(i).toString()}")
            if(adapter.getItem(i).toString() == targetItem)
                return i
        }
        return -1
    }

    override fun proceed(eventEntity: EventEntity) {
        Log.d(TAG, "proceed")
        findNavController().navigate(
            EventAddTimeEventFragmentDirections
                .actionEventAddTimeEventToEventAddValueNote(eventEntity))
    }
}