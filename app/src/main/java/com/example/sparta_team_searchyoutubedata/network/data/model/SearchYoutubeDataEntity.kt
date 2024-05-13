package com.example.sparta_team_searchyoutubedata.network.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchYoutubeDataEntity(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val regionCode: String?,
    val pageInfo: PageInfoEntity?,
    val items: List<SearchResourceEntity>?
)

data class PageInfoEntity(
    val totalResults: Int?,
    val resultsPerPage: Int?
)

data class SearchResourceEntity(
    val kind: String?,
    val etag: String?,
    val id: SearchResourceIdEntity?,
    val snippet: SearchResourceSnippetEntity?,
    val channelTitle: String?,
    val liveBroadcastContent: String?
)

data class SearchResourceIdEntity(
    val kind: String?,
    val videoId: String?,
    val channelId: String?,
    val playlistId: String?
)

data class SearchResourceSnippetEntity(
    val publishedAt: Date?,
    val channelId: String?,
    val title: String?,
    val description: String?,
    val thumbnails: ThumbnailKeyEntity?,
    val channelTitle: String?,
    val liveBroadcastContent: String?
)

data class ThumbnailKeyEntity(
    val url: String?,
    val width: UInt?,
    val height: UInt?
)

