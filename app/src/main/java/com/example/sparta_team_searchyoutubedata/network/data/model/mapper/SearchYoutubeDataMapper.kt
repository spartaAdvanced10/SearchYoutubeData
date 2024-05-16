package com.example.sparta_team_searchyoutubedata.network.data.model.mapper

import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.PageInfoResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.SearchResourceIdResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.SearchResourceResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.SearchResourceSnippetResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.SearchYoutubeDataResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ThumbnailKeyResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.PageInfoEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchResourceEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchResourceIdEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchResourceSnippetEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ThumbnailKeyEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ThumbnailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ThumbnailsResponse

fun SearchYoutubeDataResponse.toEntity() = SearchYoutubeDataEntity(
    kind = kind,
    etag = etag,
    nextPageToken = nextPageToken,
    prevPageToken = prevPageToken,
    regionCode = regionCode,
    pageInfo = pageInfo?.toEntity(),
    items = items?.map {
        it.toEntity()
    }
)

fun PageInfoResponse.toEntity() = PageInfoEntity(
    totalResults = totalResults,
    resultsPerPage = resultsPerPage
)

fun SearchResourceResponse.toEntity() = SearchResourceEntity(
    kind = kind,
    etag = etag,
    id = id?.toEntity(),
    snippet = snippet?.toEntity(),
    channelTitle = channelTitle,
    liveBroadcastContent = liveBroadcastContent
)

fun SearchResourceIdResponse.toEntity() = SearchResourceIdEntity(
    kind = kind,
    videoId = videoId,
    channelId = channelId,
    playlistId = playlistId
)

fun SearchResourceSnippetResponse.toEntity() = SearchResourceSnippetEntity(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    thumbnails = thumbnails?.toEntity(),
    channelTitle = channelTitle,
    liveBroadcastContent = liveBroadcastContent
)

fun ThumbnailsResponse.toEntity() = ThumbnailsEntity(
    default = default.toEntity(),
    medium = medium.toEntity(),
    high = high.toEntity()
)

fun ThumbnailKeyResponse.toEntity() = ThumbnailKeyEntity(
    url = url,
    width = width,
    height = height
)


