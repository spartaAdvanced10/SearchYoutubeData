package com.example.sparta_team_searchyoutubedata.homeFragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sparta_team_searchyoutubedata.databinding.ItemHomeBinding
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailActivity
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem

class HomeAdapter(
    // item click 이벤트 처리
    private val onItemClick: (HomeItemModel) -> Unit
):ListAdapter<HomeItemModel, HomeAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<HomeItemModel>(){
        override fun areItemsTheSame(oldItem: HomeItemModel, newItem: HomeItemModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HomeItemModel, newItem: HomeItemModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    abstract class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        abstract fun bind(item:HomeItemModel)
    }
    class VideoViewHolder(
        private val binding: ItemHomeBinding,
        private val context: Context,
        // item click 이벤트 처리
        private val onItemClick: (HomeItemModel) -> Unit
    ) : ViewHolder(binding.root){
        override fun bind(item: HomeItemModel) = with(binding){
            ivThumbnails.load(item.thumbnails)
            tvVideoName.text = item.title

            clHomeItem.setOnClickListener {
                val intent = Intent(context, VideoDetailActivity::class.java).apply {
                    putExtra("selectItem",
                        VideoDetailItem(item.thumbnails, item.title, item.description, false)
                    )
                }
                // item click 이벤트 처리
                onItemClick(item)
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return VideoViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), parent.context, onItemClick
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}