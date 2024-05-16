package com.example.sparta_team_searchyoutubedata.network.data.model.reponse

import com.google.gson.annotations.SerializedName

data class VideoCategoryYoutubeDataResponse (
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfoResponse?,
    @SerializedName("items") val items: List<VideoCategoryResourceResponse>?
)

data class VideoCategoryResourceResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("snippet") val snippet: VideoCategoryResourceSnippetResponse?
)

data class VideoCategoryResourceSnippetResponse(
    @SerializedName("channelId") val channelId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("assignable") val assignable: Boolean?
)
