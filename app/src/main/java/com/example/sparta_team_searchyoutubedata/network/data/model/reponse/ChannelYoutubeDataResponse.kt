package com.example.sparta_team_searchyoutubedata.network.data.model.reponse

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ChannelYoutubeDataResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfoResponse?,
    @SerializedName("items") val items: List<ChannelResourceResponse>?
)

data class ChannelResourceResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("snippet") val snippet: ChannelResourceSnippetResponse?,
    @SerializedName("contentDetails") val contentDetails: ChannelResourceContentDetailResponse?,
    @SerializedName("statistics") val statistics: ChannelResourceStatisticsResponse?,
    @SerializedName("topicDetails") val topicDetails: ChannelResourceTopicDetailsResponse?,
    @SerializedName("status") val status: ChannelResourceStatusResponse?,
    @SerializedName("brandingSettings") val brandingSettings: ChannelResourceBrandingSettingsResponse?,
    @SerializedName("auditDetails") val auditDetails: ChannelResourceAuditDetailsResponse?,
    @SerializedName("contentOwnerDetails") val contentOwnerDetails: ChannelResourceContentOwnerDetailsResponse?,
    @SerializedName("localizations") val localizations: ChannelResourceLocalizationsResponse?,
)


data class ChannelResourceSnippetResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("customUrl") val customUrl: String?,
    @SerializedName("publishedAt") val publishedAt: Date?,
    @SerializedName("thumbnails") val thumbnails: ThumbnailsResponse?,
    @SerializedName("defaultLanguage") val defaultLanguage: String?,
    @SerializedName("localized") val localized: LocalizedResponse?,
    @SerializedName("country") val country: String?

)

data class LocalizedResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?
)


data class ChannelResourceContentDetailResponse(
    @SerializedName("relatedPlaylists") val relatedPlayList: RelatedPlayListResponse?
)

data class RelatedPlayListResponse(
    @SerializedName("likes") val likes: String?,
//    @SerializedName("favorites") val favorites: String // 사용이 중단
    @SerializedName("uploads") val uploads: String?,
)

data class ChannelResourceStatisticsResponse(
    @SerializedName("viewCount") val viewCount: Long?,
//    @SerializedName("commentCount"), //지원 중단
    @SerializedName("subscriberCount") val subscriberCount: Long?,
    @SerializedName("hiddenSubscriberCount") val hiddenSubscriberCount: Boolean?,
    @SerializedName("videoCount") val videoCount: Long?

)

data class ChannelResourceTopicDetailsResponse(
    @SerializedName("topicCategories") val topicCategories: List<String>?
)

data class ChannelResourceStatusResponse(
    @SerializedName("privacyStatus") val privacyStatus: String?,
    @SerializedName("isLinked") val isLinked: Boolean?,
    @SerializedName("longUploadsStatus") val longUploadsStatus: String?,
    @SerializedName("madeForKids") val madeForKids: Boolean?,
    @SerializedName("selfDeclaredMadeForKids") val selfDeclaredMadeForKids: Boolean?
)

data class ChannelResourceBrandingSettingsResponse(
    @SerializedName("channel") val channel: BrandingSettingsChannelResponse?,
    @SerializedName("watch") val watch: BrandingSettingsWatchResponse?
)

data class BrandingSettingsChannelResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("keywords") val keywords: String?,
    @SerializedName("trackingAnalyticsAccountId") val trackingAnalyticsAccountId: String?,
    @SerializedName("unsubscribedTrailer") val unsubscribedTrailer: String?,
    @SerializedName("defaultLanguage") val defaultLanguage: String?,
    @SerializedName("country") val country: String?,
)

data class BrandingSettingsWatchResponse(
    @SerializedName("textColor") val textColor: String?,
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("featuredPlaylistId") val featuredPlaylistId: String?
)

data class ChannelResourceAuditDetailsResponse(
    @SerializedName("overallGoodStanding") val overallGoodStanding: Boolean?,
    @SerializedName("communityGuidelinesGoodStanding") val communityGuidelinesGoodStanding: Boolean?,
    @SerializedName("copyrightStrikesGoodStanding") val copyrightStrikesGoodStanding: Boolean?,
    @SerializedName("contentIdClaimsGoodStanding") val contentIdClaimsGoodStanding: Boolean?,
)

data class ChannelResourceContentOwnerDetailsResponse(
    @SerializedName("contentOwner") val contentOwner: String?,
    @SerializedName("timeLinked") val timeLinked: Date?
)

data class ChannelResourceLocalizationsResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?
)