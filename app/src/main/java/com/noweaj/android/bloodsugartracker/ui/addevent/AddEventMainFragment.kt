package com.noweaj.android.bloodsugartracker.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.R

class AddEventMainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_event_main, container, false)
        val bEventMeal: Button = root.findViewById(R.id.b_event_meal)
        bEventMeal.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToMeal())
        }
        val bEventGlucose: Button = root.findViewById(R.id.b_event_glucose)
        bEventGlucose.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToGlucose())
        }
        val bEventExercise: Button = root.findViewById(R.id.b_event_exercise)
        bEventExercise.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToExercise())
        }
        return root
    }
}