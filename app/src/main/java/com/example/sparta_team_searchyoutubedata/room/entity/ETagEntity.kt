package com.example.sparta_team_searchyoutubedata.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "etag_cache")
data class ETagEntity(
    @PrimaryKey
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "etag") val eTag: String
)
