package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.viewmodel.EventViewModel
import java.util.*

class EventFragment : Fragment() {

    private lateinit var eventViewModel: EventViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        eventViewModel =
                ViewModelProvider(this).get(EventViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_event, container, false)
        val bEventMeal: Button = root.findViewById(R.id.b_event_meal)
        bEventMeal.setOnClickListener{
            findNavController().navigate(EventFragmentDirections.actionMainEventToEventAdd(createEventEntity("meal")))
        }
        val bEventGlucose: Button = root.findViewById(R.id.b_event_glucose)
        bEventGlucose.setOnClickListener{
            findNavController().navigate(EventFragmentDirections.actionMainEventToEventAdd(createEventEntity("glucose")))
        }
        val bEventExercise: Button = root.findViewById(R.id.b_event_exercise)
        bEventExercise.setOnClickListener{
            findNavController().navigate(EventFragmentDirections.actionMainEventToEventAdd(createEventEntity("exercise")))
        }
        val tvEventDelete: TextView = root.findViewById(R.id.tv_event_delete)
        tvEventDelete.setOnClickListener{
            findNavController().navigate(EventFragmentDirections.actionMainEventToEventDelete())
        }
        return root
    }

    private fun createEventEntity(event: String): EventEntity{
        return EventEntity(
                timestamp = Calendar.getInstance().timeInMillis,
                event = event,
                value = 0,
                note = null
        )
    }
}