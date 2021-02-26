package com.noweaj.android.bloodsugartracker.ui

import android.content.Intent
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
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.ActivitySplashBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.viewmodel.SplashActivityViewModel

class SplashActivity: AppCompatActivity() {
    
    private val TAG = SplashActivity::class.java.simpleName
    
    private val viewModel: SplashActivityViewModel by viewModels {
        InjectionUtil.provideSplashActivityViewModelFactory(
            InjectionUtil.provideGlucoseRepository(
                AppDatabase.getInstance(this).chartDao(),
                AppDatabase.getInstance(this).eventDao()
            )
        )
    }
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.viewModel = viewModel
        
        observe()
    }
    
    private fun observe(){
        binding.viewModel!!.initChart.observe(this){
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d(TAG, "initChart -> LOADING: ${it.message}")
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "initChart -> SUCCESS: ${it.data}")
                    // proceed to MainActivity
                    navigateToMain()
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "initChart -> ERROR: ${it.message}")
                    // proceed to MainActivity
                    navigateToMain()
                }
            }
        }
    }
    
    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}