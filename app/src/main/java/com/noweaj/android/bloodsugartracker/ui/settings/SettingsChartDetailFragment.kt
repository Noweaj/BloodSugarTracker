package com.noweaj.android.bloodsugartracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentSettingsChartDetailBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.SettingsChartDetailViewModel

class SettingsChartDetailFragment: Fragment() {
    
    private val TAG = SettingsChartDetailFragment::class.java.simpleName
    
    private val viewModel: SettingsChartDetailViewModel by viewModels { 
        InjectionUtil.provideSettingsChartDetailViewModelFactory(
            InjectionUtil.provideGlucoseRepository(
                AppDatabase.getInstance(requireContext()).chartDao(),
                AppDatabase.getInstance(requireContext()).eventDao()
            )
        )
    }
    
    private lateinit var binding: FragmentSettingsChartDetailBinding
    private var entity: ChartEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsChartDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        entity = arguments?.get("chartEntity") as ChartEntity
        
        initView()
        
        return binding.root
    }
    
    private fun initView(){
        
    }
}