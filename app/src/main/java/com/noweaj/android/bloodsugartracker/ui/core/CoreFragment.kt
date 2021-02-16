package com.noweaj.android.bloodsugartracker.ui.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class CoreFragment: Fragment() {
    private val TAG = CoreFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

//        val callback = object: OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                Log.d(TAG, "handleOnBackPressed")
//                findNavController().navigateUp()
//                this.remove()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return view
    }
}