package com.noweaj.android.bloodsugartracker.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.databinding.FragmentSettingsBinding
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.SettingsViewModel

class SettingsFragment : Fragment(), SettingsNavigator {
    
    private val TAG = SettingsFragment::class.java.simpleName

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        binding.viewModel = settingsViewModel
        binding.viewModel!!.setNavigator(this)
        return binding.root
    }

    override fun navigateTo(id: Int) {
        Log.d(TAG, "navigateId: $id")
        when(id){
            0 -> findNavController().navigate(
                SettingsFragmentDirections
                    .actionMainSettingsToSettingsChartManager())
            1 -> findNavController().navigate(
                SettingsFragmentDirections
                    .actionMainSettingsToSettingsNotificationManager())
        }
    }
}