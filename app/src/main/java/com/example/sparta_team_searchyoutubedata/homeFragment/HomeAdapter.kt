package com.example.sparta_team_searchyoutubedata.homeFragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sparta_team_searchyoutubedata.databinding.ItemHomeBinding
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailActivity

class HomeAdapter:ListAdapter<HomeItemModel, HomeAdapter.ViewHolder>(
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
        private val binding: ItemHomeBinding
    ) : ViewHolder(binding.root){
        override fun bind(item: HomeItemModel) = with(binding){
            ivThumbnails.load(item.thumbnails)
            tvVideoName.text = item.title

            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, VideoDetailActivity::class.java)
                intent.putExtra("item", item)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return VideoViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}