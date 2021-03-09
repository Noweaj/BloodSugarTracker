package com.noweaj.android.bloodsugartracker.viewmodel

import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository

class SettingsChartDetailViewModel(
    private val repository: GlucoseRepository
): ViewModel() {
    private val TAG = SettingsChartDetailViewModel::class.java.simpleName
}