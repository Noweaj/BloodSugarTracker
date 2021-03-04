package com.noweaj.android.bloodsugartracker.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noweaj.android.bloodsugartracker.R
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import java.lang.ref.WeakReference

class SettingsViewModel : ViewModel() {
    private val TAG = SettingsViewModel::class.java.simpleName

    private var navigator: WeakReference<SettingsNavigator>? = null
    
    fun setNavigator(navigator: SettingsNavigator){
        this.navigator = WeakReference(navigator)
    }
    
    private fun navigate(id: Int){
        navigator?.let { 
            it.get()!!.navigateTo(id)
        }
    }
    
    fun onButtonClicked(v: View){
        var id = 0
        when(v.id){
            R.id.b_settings_chartmanager -> {
                id = 0
            }
            R.id.b_settings_fn1 -> {
                id = 1
            }
        }
        navigate(id)
    }
}