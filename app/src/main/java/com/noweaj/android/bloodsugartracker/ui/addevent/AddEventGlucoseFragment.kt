package com.noweaj.android.bloodsugartracker.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventGlucoseBinding

class AddEventGlucoseFragment: Fragment() {

    private lateinit var binding: FragmentEventGlucoseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventGlucoseBinding.inflate(inflater, container, false)

        return binding.root
    }
}