package com.example.sparta_team_searchyoutubedata.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.ItemMyVideoBinding
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity

class MyVideoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val myVideoList = mutableListOf<MyVideoListEntity>()

    class MyVideoViewHolder(private val binding: ItemMyVideoBinding):RecyclerView.ViewHolder(binding.root){
        fun binding(myVideoListEntity: MyVideoListEntity){

            val thumbnailUrl = myVideoListEntity.thumbnailUrl

            Glide.with(binding.root)
                .load(thumbnailUrl)
                .into(binding.ivMyVideoImg)

            binding.tvMyVideoTitle.text = myVideoListEntity.title
            binding.ivMyVideoBookmark.setImageResource(R.drawable.bookmark)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}