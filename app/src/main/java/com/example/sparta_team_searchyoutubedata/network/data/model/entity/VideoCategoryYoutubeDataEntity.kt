package com.example.sparta_team_searchyoutubedata.network.data.model.entity

import com.google.gson.annotations.SerializedName

data class VideoCategoryYoutubeDataEntity (
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val pageInfo: PageInfoEntity?,
    val items: List<VideoCategoryResourceEntity>?
)

data class VideoCategoryResourceEntity(
    val kind: String?,
    val etag: String?,
    val id: String?,
    val snippet: VideoCategoryResourceSnippetEntity?
)

data class VideoCategoryResourceSnippetEntity(
    val channelId: String?,
    val title: String?,
    val assignable: Boolean?
)