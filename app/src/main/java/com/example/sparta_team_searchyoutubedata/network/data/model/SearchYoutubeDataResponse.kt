package com.example.sparta_team_searchyoutubedata.network.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchYoutubeDataResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("regionCode") val regionCode: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfoResponse?,
    @SerializedName("items") val items: List<SearchResourceResponse>?
)

data class PageInfoResponse(
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("resultsPerPage") val resultsPerPage: Int?
)

data class SearchResourceResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: SearchResourceIdResponse?,
    @SerializedName("snippet") val snippet: SearchResourceSnippetResponse?,
    @SerializedName("channelTitle") val channelTitle: String?,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String?
)

data class SearchResourceIdResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("videoId") val videoId: String?,
    @SerializedName("channelId") val channelId: String?,
    @SerializedName("playlistId") val playlistId: String?
)

data class SearchResourceSnippetResponse(
    @SerializedName("publishedAt") val publishedAt: Date?,
    @SerializedName("channelId") val channelId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnails") val thumbnails: ThumbnailKeyResponse?,
    @SerializedName("channelTitle") val channelTitle: String?,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String?
)

data class ThumbnailKeyResponse(
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: UInt?,
    @SerializedName("height") val height: UInt?
)



