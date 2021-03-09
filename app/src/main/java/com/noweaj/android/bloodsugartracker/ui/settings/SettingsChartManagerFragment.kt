package com.noweaj.android.bloodsugartracker.ui.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.noweaj.android.bloodsugartracker.callback.SwipeCallback
import com.noweaj.android.bloodsugartracker.data.local.AppDatabase
import com.noweaj.android.bloodsugartracker.databinding.FragmentSettingsChartManagerBinding
import com.noweaj.android.bloodsugartracker.navigator.SettingsNavigator
import com.noweaj.android.bloodsugartracker.util.InjectionUtil
import com.noweaj.android.bloodsugartracker.util.SwipeController
import com.noweaj.android.bloodsugartracker.util.adapter.SettingsFragmentChartManagerListAdapter
import com.noweaj.android.bloodsugartracker.util.data.Resource
import com.noweaj.android.bloodsugartracker.viewmodel.SettingsChartManagerViewModel

class SettingsChartManagerFragment: Fragment(), SettingsNavigator, SwipeCallback {
    
    private val TAG = SettingsChartManagerViewModel::class.java.simpleName
    
    private val viewModel: SettingsChartManagerViewModel by viewModels { 
        InjectionUtil.provideSettingsChartManagerViewModelFactory(
            InjectionUtil.provideGlucoseRepository(
                AppDatabase.getInstance(requireContext()).chartDao(),
                AppDatabase.getInstance(requireContext()).eventDao()
            )
        )
    }
    
    private lateinit var binding: FragmentSettingsChartManagerBinding
    private val rvAdapter = SettingsFragmentChartManagerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsChartManagerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        
        binding.viewModel!!.setNavigator(this)
        
        setView()
        observe()
        
        return binding.root
    }
    
    private fun setView(){
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvSettingsChartmanagerChartlist.layoutManager = linearLayoutManager
        binding.rvSettingsChartmanagerChartlist.adapter = rvAdapter
        
        val swipeController = SwipeController(this)
        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.rvSettingsChartmanagerChartlist)
        
        binding.rvSettingsChartmanagerChartlist.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }
    
    private fun observe(){
        binding.viewModel!!.chartList.observe(viewLifecycleOwner){
            Log.d(TAG, "chartList ${it.status}")
            when(it.status){
                Resource.Status.LOADING -> {
                    
                }
                Resource.Status.SUCCESS -> {
                    rvAdapter.setData(it.data!!)
                }
                Resource.Status.ERROR -> {
                    
                }
            }
        }
        
        binding.viewModel!!.chartDeleted.observe(viewLifecycleOwner){
            Log.d(TAG, "delete row: ${it.data}")
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d(TAG, "chartDeleted LOADING")
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "chartDeleted SUCCESS")
                    Log.d(TAG, "updating chart list")
                    binding.viewModel!!.updateChartList()
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "chartDeleted ERROR")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel!!.updateChartList()
    }

    override fun onEditClicked(pos: Int) {
        Log.d(TAG, "onEditClicked pos: $pos")
        findNavController().navigate(
            SettingsChartManagerFragmentDirections.actionSettingsChartManagerToSettingsChartDetail(
                rvAdapter.getData()[pos]
            )
        )
    }

    override fun onDeleteClicked(pos: Int) {
        Log.d(TAG, "onDeleteClicked pos: $pos")
        if(rvAdapter.getDataSize() > 1) {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Chart")
                .setMessage("Deleting chart")
                .setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                    binding.viewModel!!.deleteChartEntity(rvAdapter.getData()[pos])
                }
                .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }.show()
        } else {
            // show snackbar that you must have at least one chart
            Snackbar.make(binding.root, "You must have at least one chart", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun navigateTo(id: Int) {
        when(id){
            0 -> {
                findNavController().navigate(
                    SettingsChartManagerFragmentDirections.actionSettingsChartManagerToSettingsChartDetail(
                        null
                    )
                )
            }
        }
    }
}