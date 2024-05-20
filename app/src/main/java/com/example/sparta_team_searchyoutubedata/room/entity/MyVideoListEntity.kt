package com.example.sparta_team_searchyoutubedata.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// 아직 미구현,
// 추후 좋아요 완료 되었을 때 이어서 구현 예정

@Entity(tableName = "my_video_list")
data class MyVideoListEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val timestamp: Long = System.currentTimeMillis(),
    val thumbnailUrl: String,
    val isLiked: Boolean,
    val description:String
)