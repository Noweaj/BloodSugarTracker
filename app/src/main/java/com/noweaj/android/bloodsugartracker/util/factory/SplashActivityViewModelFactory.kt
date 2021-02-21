package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.viewmodel.SplashActivityViewModel

class SplashActivityViewModelFactory(
    private val chartRepository: ChartRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashActivityViewModel(chartRepository) as T
    }
}