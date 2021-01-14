package com.noweaj.android.bloodsugartracker.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.R

class AddEventFragment : Fragment() {

    private lateinit var addEventViewModel: AddEventViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        addEventViewModel =
                ViewModelProvider(this).get(AddEventViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_addevent, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        addEventViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}