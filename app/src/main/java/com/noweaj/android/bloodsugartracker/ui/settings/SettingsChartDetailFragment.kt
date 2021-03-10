package com.noweaj.android.bloodsugartracker.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentSettingsChartDetailBinding
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.viewmodel.SettingsChartDetailViewModel

class SettingsChartDetailFragment: Fragment(), SettingsNavigator {
    
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
        
        binding.viewModel!!.setNavigator(this)
        entity = arguments?.get("chartEntity") as ChartEntity?
        
        initView()
        observe()
        
        return binding.root
    }
    
    private fun initView(){
        entity?.let { 
            Log.d(TAG, "ChartEntity is not null")
            binding.viewModel!!.setChartEntity(it)
        }
        entity?: Log.d(TAG, "ChartEntity is null")
    }
    
    private fun observe(){
        binding.viewModel!!.chartSaved.observe(viewLifecycleOwner){
            Log.d(TAG, "chartSaved ${it.status}")
            when(it.status){
                Resource.Status.LOADING -> {
                    
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "chartSaved: ${it.data}")
                    findNavController().navigateUp()
                }
                Resource.Status.ERROR -> {
                    
                }
            }
        }
    }

    override fun navigateTo(id: Int) {
        // navigate to chartmanager
        
    }
}