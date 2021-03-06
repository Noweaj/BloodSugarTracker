package com.noweaj.android.bloodsugartracker.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noweaj.android.bloodsugartracker.data.repository.ChartRepository
import com.noweaj.android.bloodsugartracker.data.repository.GlucoseRepository
import com.noweaj.android.bloodsugartracker.viewmodel.SplashActivityViewModel

class SplashActivityViewModelFactory(
    private val repository: GlucoseRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashActivityViewModel(repository) as T
    }
}