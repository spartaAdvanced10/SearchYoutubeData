package com.example.sparta_team_searchyoutubedata.search

data class SearchItem (
    val thumbnail: String,
    val title: String,
    val description: String,
    val isLiked: Boolean = false,
    val nextPageToken:String = "",
    val prevPageToken:String = ""
)