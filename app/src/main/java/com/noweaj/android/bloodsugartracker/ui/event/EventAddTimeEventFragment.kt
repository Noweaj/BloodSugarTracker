package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddTimeEventBinding
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddTimeEventViewModel

class EventAddTimeEventFragment: Fragment() {

    private val TAG = EventAddTimeEventFragment::class.java.simpleName

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddTimeEventBinding.inflate(inflater, container, false)
        binding.viewModel = EventAddTimeEventViewModel()

        return binding.root
    }
}