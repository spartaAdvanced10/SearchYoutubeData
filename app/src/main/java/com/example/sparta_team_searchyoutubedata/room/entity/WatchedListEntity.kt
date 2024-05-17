package com.example.sparta_team_searchyoutubedata.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "watched_list")
data class WatchedListEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val timestamp:Long = System.currentTimeMillis()
)


