package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddValueNoteBinding

class EventAddValueNoteFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEventAddValueNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
}