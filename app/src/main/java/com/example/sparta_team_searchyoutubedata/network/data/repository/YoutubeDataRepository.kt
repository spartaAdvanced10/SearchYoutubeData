package com.example.sparta_team_searchyoutubedata.network.data.repository

import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryResourceEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoCategoryYoutubeDataResponse
import retrofit2.http.Query

interface YoutubeDataRepository {

    suspend fun getSearch(
        part: String = "snippet",
        channelType: String = "any",
        maxResults: Int = 5,
        order: String, //date, rating, relevance, title, viewCount, videoCount
        q :String,
        relevanceLanguage:String = "ko",
    ): SearchYoutubeDataEntity

    suspend fun getChannel(
        part: String = "snippet",
        maxResults: Int = 5,
        id: String
    ) : ChannelYoutubeDataEntity

    suspend fun getVideoCategory(
        part: String = "snippet",
        regionCode: String = "KR" //미국: US (United States) 영국: GB (United Kingdom) 캐나다: CA (Canada) 일본: JP (Japan) 한국: KR (Korea, Republic of)
    ) : VideoCategoryYoutubeDataEntity

    suspend fun getVideos(
        part: String = "snippet",
        chart:String = "mostPopular",
        maxResults:Int = 5,
        videoCategoryId: String
    ): VideoYoutubeDataEntity
}

