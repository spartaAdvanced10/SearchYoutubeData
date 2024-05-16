package com.example.sparta_team_searchyoutubedata.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import coil.load
import com.example.sparta_team_searchyoutubedata.databinding.ItemSearchBinding

class SearchListAdapter
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

    class VideoThumbnailViewHolder(
        private val binding: ItemSearchBinding
    ): ViewHolder(binding.root){
        override fun bind(item: SearchItem) = with(binding) {
            ivSearchImage.load(item.thumbnail)
            tvSearchTitle.text = item.title
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
            )
        )
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}