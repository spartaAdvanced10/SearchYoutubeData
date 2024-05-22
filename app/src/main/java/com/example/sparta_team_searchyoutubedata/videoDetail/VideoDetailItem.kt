package com.example.sparta_team_searchyoutubedata.videoDetail

import java.io.Serializable

data class VideoDetailItem(
    val thumbnail: String,
    val title: String,
    val description: String,
    val isLiked: Boolean
) : Serializable
