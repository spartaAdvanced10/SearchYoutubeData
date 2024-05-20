package com.example.sparta_team_searchyoutubedata.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.databinding.ItemMyVideosBinding
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class MyVideoListAdapter(
    private val onDeleteItemClick: (MyVideoListEntity) -> Unit,
    private val onItemClick: (MyVideoListEntity) -> Unit

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>(

) {

    var myVideoList = listOf<MyVideoListEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(
        private val binding: ItemMyVideosBinding,
        private val onItemClick: (MyVideoListEntity) -> Unit,
        private val onDeleteItemClick: (MyVideoListEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(myVideoList: MyVideoListEntity) {
            val thumbnailUrl = myVideoList.thumbnailUrl

            if (thumbnailUrl != null) {
                Glide.with(binding.root).load(thumbnailUrl).into(binding.ivMyVideoImg)
            }

            binding.tvMyVideoTitle.text = myVideoList.title
            binding.tvMyVideAgo.text = calculateDaysAgo(myVideoList.timestamp)

            binding.root.setOnClickListener{
                onItemClick(myVideoList)
            }

            binding.ivMyVideoBookmark.setOnClickListener {
                onDeleteItemClick(myVideoList)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMyVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick, onDeleteItemClick)
    }

    override fun getItemCount(): Int {
        return myVideoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(myVideoList[position])
    }
}

// Timestamp 형식 변환

fun formatTimestamp(timestamp: Long):String {
    val simpleDataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date(timestamp)
    return simpleDataFormat.format(date)
}

// 지난 일 수 계산

fun calculateDaysAgo(timestamp: Long): String{
    val currentDate = System.currentTimeMillis()
    val diffInMillis = currentDate - timestamp
    val daysAgo = TimeUnit.MILLISECONDS.toDays(diffInMillis)
    return if (daysAgo == 0L){
        "Today"
    } else {
        "$daysAgo days ago"
    }

}