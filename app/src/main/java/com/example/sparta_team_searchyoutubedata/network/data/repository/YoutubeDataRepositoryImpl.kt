package com.example.sparta_team_searchyoutubedata.network.data.repository

import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.mapper.toEntity
import com.example.sparta_team_searchyoutubedata.network.data.remote.YoutubeDataRemoteResource

class YoutubeDataRepositoryImpl(
    private val remoteResource: YoutubeDataRemoteResource
): YoutubeDataRepository {
    override suspend fun getSearch(
        part: String,
        channelType: String,
        maxResults: Int,
        order: String,
        q: String,
        relevanceLanguage: String
    ): SearchYoutubeDataEntity = remoteResource.getYoutubeSearchData(
        part,
        channelType,
        maxResults,
        order,
        q,
        relevanceLanguage
    ).toEntity()

    override suspend fun getChannel(
        part: String,
        maxResults: Int,
        id: String
    ): ChannelYoutubeDataEntity = remoteResource.getYoutubeChannelData(
        part,
        maxResults,
        id
    ).toEntity()

    override suspend fun getVideoCategory(
        part: String,
        regionCode: String
    ): VideoCategoryYoutubeDataEntity = remoteResource.getYoutubeVideoCategoriesData(
        part,
        regionCode
    ).toEntity()

    override suspend fun getVideos(
        part: String,
        chart:String,
        maxResults: Int,
        videoCategoryId: String
    ): VideoYoutubeDataEntity =remoteResource.getYoutubeVideosData(
        part = part,
        chart = chart,
        maxResults = maxResults,
        videoCategoryId = videoCategoryId
    ).toEntity()
}