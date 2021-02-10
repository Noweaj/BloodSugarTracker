package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddBinding
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddViewModel

class EventAddFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddBinding.inflate(inflater, container, false)
        binding.viewModel = EventAddViewModel()
        return binding.root
    }
}