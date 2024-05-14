package com.example.sparta_team_searchyoutubedata.network.data.model.mapper

import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryResourceEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryResourceSnippetEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoCategoryYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoCategoryResourceResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoCategoryResourceSnippetResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoCategoryYoutubeDataResponse

fun VideoCategoryYoutubeDataResponse.toEntity(): VideoCategoryYoutubeDataEntity {
    return VideoCategoryYoutubeDataEntity(
        kind = kind,
        etag = etag,
        nextPageToken = nextPageToken,
        prevPageToken = prevPageToken,
        pageInfo = pageInfo?.toEntity(),
        items = items?.map { it.toEntity() }
    )
}

fun VideoCategoryResourceResponse.toEntity(): VideoCategoryResourceEntity {
    return VideoCategoryResourceEntity(
        kind = kind,
        etag = etag,
        id = id,
        snippet = snippet?.toEntity()
    )
}

fun VideoCategoryResourceSnippetResponse.toEntity(): VideoCategoryResourceSnippetEntity {
    return VideoCategoryResourceSnippetEntity(
        channelId = channelId,
        title = title,
        assignable = assignable
    )
}
