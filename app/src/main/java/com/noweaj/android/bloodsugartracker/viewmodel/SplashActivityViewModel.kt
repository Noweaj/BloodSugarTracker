package com.noweaj.android.bloodsugartracker.viewmodel

import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository

class SplashActivityViewModel(
    private val chartRepository: ChartRepository
): ViewModel() {
}