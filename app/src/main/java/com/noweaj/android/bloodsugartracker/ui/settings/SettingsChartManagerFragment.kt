package com.noweaj.android.bloodsugartracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentSettingsChartManagerBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.SettingsChartManagerViewModel

class SettingsChartManagerFragment: Fragment() {
    
    private val TAG = SettingsChartManagerViewModel::class.java.simpleName
    
    private val viewModel: SettingsChartManagerViewModel by viewModels { 
        InjectionUtil.provideSettingsChartManagerViewModelFactory(
            InjectionUtil.provideGlucoseRepository(
                AppDatabase.getInstance(requireContext()).chartDao(),
                AppDatabase.getInstance(requireContext()).eventDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsChartManagerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}