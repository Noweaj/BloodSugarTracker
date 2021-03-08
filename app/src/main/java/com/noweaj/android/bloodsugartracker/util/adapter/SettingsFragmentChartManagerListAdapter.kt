package com.noweaj.android.bloodsugartracker.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.databinding.ItemSettingsChartManagerBinding

class SettingsFragmentChartManagerListAdapter(
    
): RecyclerView.Adapter<SettingsFragmentChartManagerListAdapter.ChartListViewHolder>() {
    
    private val TAG = SettingsFragmentChartManagerListAdapter::class.java.simpleName
    private var chartEntities = mutableListOf<ChartEntity>()
    
    fun setData(
        chartEntities: List<ChartEntity>
    ){
        this.chartEntities.clear()
        this.chartEntities.addAll(chartEntities)
        notifyDataSetChanged()
    }
    
    fun getData(): List<ChartEntity>{
        return chartEntities
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartListViewHolder {
        val binding: ItemSettingsChartManagerBinding = ItemSettingsChartManagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChartListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chartEntities.size
    }

    override fun onBindViewHolder(holder: ChartListViewHolder, position: Int) {
        chartEntities?.let {
            holder.bind(it[position])
        }
    }

    class ChartListViewHolder(
        private val binding: ItemSettingsChartManagerBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(
            chartEntity: ChartEntity
        ){
            binding.tvSettingsChartmanagerTitle.text = chartEntity.title
            binding.tvSettingsChartmanagerDescription.text = chartEntity.description
        }
    }
}