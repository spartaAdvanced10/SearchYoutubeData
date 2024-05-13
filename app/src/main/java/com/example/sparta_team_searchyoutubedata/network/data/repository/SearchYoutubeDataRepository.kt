package com.example.sparta_team_searchyoutubedata.network.data.repository

import com.example.sparta_team_searchyoutubedata.network.data.model.SearchYoutubeDataEntity
import retrofit2.http.Query

interface SearchYoutubeDataRepository {

    suspend fun getSearchImage(
        part: String = "snippet",
        channelType: String = "any",
        maxResults: UInt = 5u,
        order: String, //date, rating, relevance, title, viewCount, videoCount
        q :String,
        relevanceLanguage:String = "ko",
    ): SearchYoutubeDataEntity
}