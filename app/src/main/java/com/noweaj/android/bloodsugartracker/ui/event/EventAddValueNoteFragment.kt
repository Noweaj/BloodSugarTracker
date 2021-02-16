package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddValueNoteBinding
import com.noweaj.android.bloodsugartracker.navigator.EventNavigator
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddValueNoteViewModel

class EventAddValueNoteFragment: Fragment(), EventNavigator {

    private val TAG = EventAddValueNoteFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddValueNoteBinding.inflate(inflater, container, false)

        var entity = arguments?.get("eventEntity") as EventEntity
        binding.viewModel = EventAddValueNoteViewModel(entity)

        binding.viewModel!!.setNavigator(this)

        return binding.root
    }

    override fun proceed(eventEntity: EventEntity) {
        Log.d(TAG, "proceed")
        // save to db
        // go back to chart
    }
}