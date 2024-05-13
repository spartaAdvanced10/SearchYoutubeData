package com.example.sparta_team_searchyoutubedata.network.data.remote
import com.example.sparta_team_searchyoutubedata.network.data.model.SearchYoutubeDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

//https://developers.google.com/youtube/v3/docs/search/list?hl=ko

interface SearchYoutubeDataRemoteResource {

    @GET("search")
    suspend fun getYoutubeSearchData(
        @Query("part") part: String,
        @Query("channelType") channelType: String ,
        @Query("maxResults") maxResults: UInt,
        @Query("order") order: String, //date, rating, relevance, title, viewCount, videoCount
        @Query("q") q :String,
        @Query("relevanceLanguage") relevanceLanguage:String,
    ): SearchYoutubeDataResponse
}