package com.example.sparta_team_searchyoutubedata.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.databinding.ItemWatchedBinding
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

class WatchedAdapter(
    private val onItemClick: (WatchedListEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var watchedList = listOf<WatchedListEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class WatchedListViewHolder(
        private val binding: ItemWatchedBinding,
        private val onItemClick: (WatchedListEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(watchedListEntity: WatchedListEntity) {

            val thumbnailUrl = watchedListEntity.thumbnailUrl

            Glide.with(binding.root)
                .load(thumbnailUrl)
                .into(binding.ivWatchedImg)

            binding.tvWatchedTitle.text = watchedListEntity.title
            Log.d("watched ", "${watchedListEntity.title}")

            binding.root.setOnClickListener{
                onItemClick(watchedListEntity)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemWatchedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchedListViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int {
        return watchedList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WatchedListViewHolder).binding(watchedList[position])
    }
}