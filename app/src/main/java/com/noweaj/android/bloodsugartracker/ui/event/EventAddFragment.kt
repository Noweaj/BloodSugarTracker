package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddBinding
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddViewModel

class EventAddFragment: Fragment() {

    private val TAG = EventAddFragment::class.java.simpleName

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddBinding.inflate(inflater, container, false)
        binding.viewModel = EventAddViewModel()

//        setupActionBarWithNavController(, findNavController())

//        appBarConfiguration = AppBarConfiguration(findNavController().graph)
//        setupActionBarWithNavController(findNavController(), appBarConfiguration)


        // for handling pressing back button
        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d(TAG, "OnBackPressedCallback")
                findNavController().navigateUp()
                this.remove()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        return binding.root
    }
}