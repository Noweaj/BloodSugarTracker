package com.noweaj.android.bloodsugartracker.ui

import android.content.Intent
import android.graphics.Color
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
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.ActivitySplashBinding
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.util.chart.DayAxisValueFormatter
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
        
//        setupChart()
        observe()
    }
    
    private fun observe(){
        binding.viewModel!!.initChart.observe(this){
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d(TAG, "initChart -> LOADING: ${it.message}")
                    showProgress(true)
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "initChart -> SUCCESS: ${it.data}")
                    showProgress(false)
                    // proceed to MainActivity
                    navigateToMain()
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "initChart -> ERROR: ${it.message}")
                    showProgress(false)
                    // proceed to MainActivity
                    navigateToMain()
                }
            }
        }
    }
    
    private fun showProgress(showFlag: Boolean){
        if(showFlag){
            binding.pbSplash.visibility = View.VISIBLE
            binding.tvSplashLoading.visibility = View.VISIBLE
        } else {
            binding.pbSplash.visibility = View.GONE
            binding.tvSplashLoading.visibility = View.GONE
        }
    }
    
    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
//    
//    private fun setupChart(){
//        binding.ccSplashTest.setBackgroundColor(Color.WHITE)
//        binding.ccSplashTest.drawOrder = arrayOf(
//            CombinedChart.DrawOrder.BAR,
//            CombinedChart.DrawOrder.LINE
//        )
//        val data = CombinedData()
//        data.setData(getBarData())
//
//        val yAxis = binding.ccSplashTest.axisLeft
//
//        yAxis.mAxisMaximum = 200f
//
//        val xAxis = binding.ccSplashTest.xAxis
//        xAxis.granularity = 1f
//        xAxis.valueFormatter = DayAxisValueFormatter(binding.ccSplashTest)
//
//        binding.ccSplashTest.data = data
//    }
//
//    private fun getBarData(): BarData {
//
//        val entries = ArrayList<BarEntry>()
//
//        entries.add(BarEntry(1f, 85f))
//        entries.add(BarEntry(2f, 112f))
//        entries.add(BarEntry(3f, 198f))
//        entries.add(BarEntry(4f, 165f))
//        entries.add(BarEntry(5f, 130f))
//        entries.add(BarEntry(6f, 184f))
//
//        val set = BarDataSet(entries, "Bar")
//        set.color = Color.rgb(60, 220, 78)
//        set.valueTextColor = Color.rgb(60, 220, 78)
//        set.valueTextSize = 10f
//        set.axisDependency = YAxis.AxisDependency.LEFT
//
//        val data = BarData(set)
//        data.barWidth = 0.45f
//
//        return data
//    }
}