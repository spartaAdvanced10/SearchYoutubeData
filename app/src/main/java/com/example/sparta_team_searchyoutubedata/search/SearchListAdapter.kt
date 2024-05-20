package com.example.sparta_team_searchyoutubedata.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sparta_team_searchyoutubedata.databinding.ItemSearchBinding
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailActivity
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem

class SearchListAdapter(
    private val onItemClick: (SearchItem) -> Unit
)
    :ListAdapter<SearchItem, SearchListAdapter.ViewHolder>(
        object: DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }
        }
    ){


    abstract class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        abstract fun bind(item: SearchItem)
    }

    inner class VideoThumbnailViewHolder(
        private val binding: ItemSearchBinding,
        private val context: Context,
        private val onItemClick: (SearchItem) -> Unit
    ): ViewHolder(binding.root){
        override fun bind(item: SearchItem) = with(binding) {
            ivSearchImage.load(item.thumbnail)
            tvSearchTitle.text = item.title

            parent.setOnClickListener {
                val intent = Intent(context, VideoDetailActivity::class.java).apply {
                    putExtra("selectItem",VideoDetailItem(item.thumbnail, item.title, item.description, false))
                }
                onItemClick(item)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListAdapter.ViewHolder {
        return VideoThumbnailViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), parent.context, onItemClick
        )
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}