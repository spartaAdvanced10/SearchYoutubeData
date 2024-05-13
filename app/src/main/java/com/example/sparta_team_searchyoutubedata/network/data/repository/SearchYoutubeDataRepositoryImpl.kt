package com.example.sparta_team_searchyoutubedata.network.data.repository

import com.example.sparta_team_searchyoutubedata.network.data.model.SearchYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.toEntity
import com.example.sparta_team_searchyoutubedata.network.data.remote.SearchYoutubeDataRemoteResource

class SearchYoutubeDataRepositoryImpl(
    private val remoteResource: SearchYoutubeDataRemoteResource
): SearchYoutubeDataRepository {
    override suspend fun getSearchImage(
        part: String,
        channelType: String,
        maxResults: UInt,
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
}