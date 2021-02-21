package com.noweaj.android.bloodsugartracker.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.ActivitySplashBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.SplashActivityViewModel

class SplashActivity: AppCompatActivity() {
    
    private val TAG = SplashActivity::class.java.simpleName
    
    private val viewModel: SplashActivityViewModel by viewModels {
        InjectionUtil.provideSplashActivityViewModelFactory(
            InjectionUtil.provideChartRepository(AppDatabase.getInstance(this).chartDao())
        )
    }
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }
    
    private fun navigateToMain(){
        
    }
}