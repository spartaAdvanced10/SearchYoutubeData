package com.example.sparta_team_searchyoutubedata.network.data.remote
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelYoutubeDataResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.SearchYoutubeDataResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoCategoryYoutubeDataResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoYoutubeDataResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

//https://developers.google.com/youtube/v3/docs/search/list?hl=ko

interface YoutubeDataRemoteResource {

    @GET("search")
    suspend fun getYoutubeSearchData(
        @Query("part") part: String,
        @Query("channelType") channelType: String ,
        @Query("maxResults") maxResults: Int,
        @Query("order") order: String, //date, rating, relevance, title, viewCount, videoCount
        @Query("q") q :String,
        @Query("relevanceLanguage") relevanceLanguage:String,
        @Query("pageToken") pageToken: String
    ): SearchYoutubeDataResponse

    @GET("channels")
    suspend fun getYoutubeChannelData(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("id") id: String
    ): ChannelYoutubeDataResponse

    @GET("videoCategories")
    suspend fun getYoutubeVideoCategoriesData(
        @Query("part") part: String,
        @Query("regionCode") regionCode: String
    ): VideoCategoryYoutubeDataResponse

    @GET("videos")
    suspend fun getYoutubeVideosData(
        @Query("part") part: String,
        @Query("chart") chart: String,
        @Query("maxResults") maxResults: Int,
        @Query("videoCategoryId") videoCategoryId: String
    ): VideoYoutubeDataResponse
}