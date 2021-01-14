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
        val b_event_meal: Button = root.findViewById(R.id.b_event_meal)
        b_event_meal.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToMeal())
        }
        val b_event_bloodsugar: Button = root.findViewById(R.id.b_event_bloodsugar)
        b_event_bloodsugar.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToBloodsugar())
        }
        val b_event_exercise: Button = root.findViewById(R.id.b_event_exercise)
        b_event_exercise.setOnClickListener{
            findNavController().navigate(AddEventMainFragmentDirections.actionEventMainToExercise())
        }
        return root
    }
}