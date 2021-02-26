package com.noweaj.android.bloodsugartracker.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity
import com.noweaj.android.bloodsugartracker.databinding.ItemChartBinding
import com.noweaj.android.bloodsugartracker.util.chart.ChartSpec

class ChartListAdapter(
    
): RecyclerView.Adapter<ChartListAdapter.ChartListViewHolder>() {
    
    private var chartSpec: ChartSpec? = null
    
    fun setData(
        chartSpec: ChartSpec
    ){
        this.chartSpec = chartSpec
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartListViewHolder {
        val binding: ItemChartBinding = ItemChartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChartListViewHolder, position: Int) {
        chartSpec?.let {
//            holder.bind(
//                it.chartEntities[position],
//                it.eventEntitiesPerChart[position]
//            )
        }
    }

    override fun getItemCount(): Int {
        chartSpec?.let { 
//            return it.chartEntities.size
        }
        return 0
    }

    class ChartListViewHolder(
        private val binding: ItemChartBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(
            chartEntity: ChartEntity,
            eventEntities: List<EventEntity>
        ){
            
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