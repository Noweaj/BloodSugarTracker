package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddTimeEventBinding
import com.noweaj.android.bloodsugartracker.navigator.EventNavigator
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddTimeEventViewModel
import java.util.*

class EventAddTimeEventFragment: Fragment(), EventNavigator {

    private val TAG = EventAddTimeEventFragment::class.java.simpleName

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddTimeEventBinding.inflate(inflater, container, false)

        var entity = arguments?.get("eventEntity") as EventEntity
        Log.d(TAG, "${entity.event}")
        entity.timestamp = Calendar.getInstance().timeInMillis
        binding.viewModel = EventAddTimeEventViewModel(entity)

        binding.viewModel!!.setNavigator(this)

        return binding.root
    }

    override fun proceed(eventEntity: EventEntity) {
        Log.d(TAG, "proceed")
        findNavController().navigate(
            EventAddTimeEventFragmentDirections
                .actionEventAddTimeEventToEventAddValueNote(eventEntity))
    }
}