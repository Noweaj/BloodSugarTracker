package com.noweaj.android.bloodsugartracker.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentEventAddValueNoteBinding
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.viewmodel.EventAddValueNoteViewModel

class EventAddValueNoteFragment: Fragment() {

    private val TAG = EventAddValueNoteFragment::class.java.simpleName

    private val viewModel: EventAddValueNoteViewModel by viewModels {
        InjectionUtil.provideEventAddValueNoteViewModelFactory(
            InjectionUtil.provideGlucoseRepository(
                AppDatabase.getInstance(requireContext()).chartDao(),
                AppDatabase.getInstance(requireContext()).eventDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventAddValueNoteBinding.inflate(inflater, container, false)

        var entity = arguments?.get("eventEntity") as EventEntity
        binding.viewModel = viewModel

        binding.viewModel!!.setEventEntity(entity)

//        observe(binding)

        return binding.root
    }

//    private fun observe(binding: FragmentEventAddValueNoteBinding){
//        binding.viewModel!!.insertEvent.observe(viewLifecycleOwner){
//            when(it.status){
//                Resource.Status.LOADING -> {
//                    Log.d(TAG, "LOADING")
//                }
//                Resource.Status.SUCCESS -> {
//                    Log.d(TAG, "SUCCESS ${it.data}")
//                    findNavController().navigate(
//                        EventAddValueNoteFragmentDirections
//                            .actionEventAddValueNoteToChartFragment())
//                }
//                Resource.Status.ERROR -> {
//                    Log.d(TAG, "ERROR")
//                }
//            }
//        }
//    }
}