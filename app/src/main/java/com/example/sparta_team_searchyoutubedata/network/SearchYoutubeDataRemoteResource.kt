package com.example.sparta_team_searchyoutubedata.network
import retrofit2.http.GET
import retrofit2.http.Query

//https://developers.google.com/youtube/v3/docs/search/list?hl=ko

interface SearchYoutubeDataRemoteResource {

    @GET("v3/search")
    suspend fun getYoutubeSearchData(
        @Query("part") part: String = "snippet",
        @Query("channelType") channelType: String = "any",
        @Query("maxResults") maxResults: UInt = 50u,
        @Query("order") order: String, //date, rating, relevance, title, viewCount, videoCount
        @Query("q") q :String,
        @Query("relevanceLanguage") relevanceLanguage:String = "ko",
    )
}