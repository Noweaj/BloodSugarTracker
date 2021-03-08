package com.noweaj.android.bloodsugartracker.util.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.databinding.ItemChartChartListBinding
import com.noweaj.android.bloodsugartracker.util.chart.ChartSpec
import com.noweaj.android.bloodsugartracker.util.chart.DayAxisValueFormatter

class ChartFragmentListAdapter(
    
): RecyclerView.Adapter<ChartFragmentListAdapter.ChartListViewHolder>() {
    
    private val TAG = ChartFragmentListAdapter::class.java.simpleName
    private var chartSpecs = mutableListOf<ChartSpec>()
    
    fun setData(
        chartSpecs: List<ChartSpec>
    ){
        this.chartSpecs.clear()
        this.chartSpecs.addAll(chartSpecs)
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartListViewHolder {
        val binding: ItemChartChartListBinding = ItemChartChartListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChartListViewHolder, position: Int) {
        chartSpecs?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return chartSpecs.size
    }

    class ChartListViewHolder(
        private val binding: ItemChartChartListBinding
    ): RecyclerView.ViewHolder(binding.root){
        private val TAG = ChartListViewHolder::class.java.simpleName
        
        fun bind(
            chartSpec: ChartSpec
        ){
            binding.tvChartTitle.text = chartSpec.chartEntity.title
            if(chartSpec.chartEntity.description.isBlank()){
                binding.tvChartDescription.visibility = View.GONE
            } else {
                binding.tvChartDescription.text = chartSpec.chartEntity.description
            }
            binding.ccRvChart.setBackgroundColor(Color.WHITE)
            binding.ccRvChart.drawOrder = arrayOf(
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE
            )
            val data = CombinedData()
            data.setData(getBarData(chartSpec.eventEntities))

//            val limitLine_140 = LimitLine(140f, "2hr")
//            limitLine_140.lineWidth = 4f
//            limitLine_140.enableDashedLine(10f, 10f, 0f)
//            limitLine_140.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
//            limitLine_140.textSize = 10f
//
//            val limitLine_180 = LimitLine(180f, "1hr")
//            limitLine_180.lineWidth = 4f
//            limitLine_180.enableDashedLine(10f, 10f, 0f)
//            limitLine_180.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
//            limitLine_180.textSize = 10f

            val yAxis = binding.ccRvChart.axisLeft
//            yAxis.addLimitLine(limitLine_140)
//            yAxis.addLimitLine(limitLine_180)

            yAxis.mAxisMaximum = 200f

            val xAxis = binding.ccRvChart.xAxis
            xAxis.granularity = 1f
            xAxis.valueFormatter = DayAxisValueFormatter(binding.ccRvChart)
            
            binding.ccRvChart.data = data
        }
        
        private fun getBarData(entities: List<EventEntity>): BarData{
            val entries = ArrayList<BarEntry>()
//            for(i in entities.indices){
//                entries.add(BarEntry(
//                    (i+1) as Float, entities[i].value as Float
//                ))
//            }

            entries.add(BarEntry(1f, 85f))
            entries.add(BarEntry(2f, 112f))
            entries.add(BarEntry(3f, 198f))
            entries.add(BarEntry(4f, 165f))
            entries.add(BarEntry(5f, 130f))
            entries.add(BarEntry(6f, 184f))
            
            val set = BarDataSet(entries, "Bar")
            set.color = Color.rgb(60, 220, 78)
            set.valueTextColor = Color.rgb(60, 220, 78)
            set.valueTextSize = 10f
            set.axisDependency = YAxis.AxisDependency.LEFT
            
            val data = BarData(set)
            data.barWidth = 0.45f
            
            return data
        }
    }
}

/*

class UserListAdapter(
        private val listener: GithubUserItemListener
): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    private val TAG = UserListAdapter::class.java.simpleName
    private var userEntities: List<GithubUser> = emptyList()

    fun setData(newEntities: List<GithubUser>){
        this.userEntities = newEntities
        notifyDataSetChanged()
    }

    fun refreshData(){
        Log.d(TAG, "refreshData")
        this.userEntities = this.userEntities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding: ItemUsersBinding = ItemUsersBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userEntities[position])
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
//        return UserListViewHolder(ItemUsersBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//        ))
//    }
//
//    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
//        holder.itemView.tv_login.setText(userEntities[position].login)
//        holder.itemView.tv_url.setText(userEntities[position].html_url)
//
//        Glide
//            .with(context)
//            .load(userEntities[position].avatar_url)
//            .apply(RequestOptions()
//                .fitCenter()
//                .placeholder(R.mipmap.ic_launcher_round)
//                .error(R.mipmap.ic_launcher_round)
//            )
//            .into(holder.itemView.iv_avatar)
//
//        holder.itemView.iv_liked.setImageResource(R.drawable.ic_baseline_favorite_border_48)
//    }

    override fun getItemCount(): Int {
        return this.userEntities.size
    }

    class UserListViewHolder(
            private val binding: ItemUsersBinding,
            private val listener: GithubUserItemListener
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: GithubUser){
            binding.tvLogin.text = item.login
            binding.tvUrl.text = item.html_url
            Glide.with(binding.root)
                    .load(item.avatar_url)
                    .apply(RequestOptions()
                            .fitCenter()
                            .placeholder(R.mipmap.ic_launcher_round)
                            .error(R.mipmap.ic_launcher_round))
                    .into(binding.ivAvatar)

            if(item.liked)
                binding.ivLiked.setImageResource(R.drawable.ic_baseline_favorite_48)
            else
                binding.ivLiked.setImageResource(R.drawable.ic_baseline_favorite_border_48)

            binding.ivLiked.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }
}

*/