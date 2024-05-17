package com.example.sparta_team_searchyoutubedata.homeFragment

import java.io.Serializable

data class HomeItemModel(
    var title:String,
    var thumbnails:String,
    var description: String,
    var channelID :String
) : Serializable
