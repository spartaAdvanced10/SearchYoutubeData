package com.example.sparta_team_searchyoutubedata.network.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date
import kotlin.math.sin

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

fun ThumbnailKeyResponse.toEntity() = ThumbnailKeyEntity(
     url = url,
     width = width,
     height = height
)


