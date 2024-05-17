package com.example.sparta_team_searchyoutubedata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.databinding.ItemRecordBinding
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

class WatchedListAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder> (){

    val watchedList = mutableListOf<WatchedListEntity>()

    class WatchedViewHolder(
        private val binding: ItemRecordBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(watchedListEntity: WatchedListEntity){
            val thumbnailUrl = watchedListEntity.thumbnailUrl

            Glide.with(binding.root)
                .load(thumbnailUrl)
                .into(binding.ivRecordImg)

            binding.ivRecordTitle.text = watchedListEntity.title
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchedViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return watchedList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WatchedViewHolder).bind(watchedList[position])
    }
}