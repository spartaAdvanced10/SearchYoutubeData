package com.example.sparta_team_searchyoutubedata.network.data.model.entity

import java.util.Date

data class ChannelYoutubeDataEntity(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val pageInfo: PageInfoEntity?,
    val items: List<ChannelResourceEntity>?
)

data class ChannelResourceEntity(
    val kind: String?,
    val etag: String?,
    val id: String?,
    val snippet: ChannelResourceSnippetEntity?,
    val contentDetails: ChannelResourceContentDetailEntity?,
    val statistics: ChannelResourceStatisticsEntity?,
    val topicDetails: ChannelResourceTopicDetailsEntity?,
    val status: ChannelResourceStatusEntity?,
    val brandingSettings: ChannelResourceBrandingSettingsEntity?,
    val auditDetails: ChannelResourceAuditDetailsEntity?,
    val contentOwnerDetails: ChannelResourceContentOwnerDetailsEntity?,
    val localizations: ChannelResourceLocalizationsEntity?,
)


data class ChannelResourceSnippetEntity(
    val title: String?,
    val description: String?,
    val customUrl: String?,
    val publishedAt: Date?,
    val thumbnails: ThumbnailsEntity?,
    val defaultLanguage: String?,
    val localized: LocalizedEntity?,
    val country: String?

)

data class LocalizedEntity(
    val title: String?,
    val description: String?
)


data class ChannelResourceContentDetailEntity(
    val relatedPlayList: RelatedPlayListEntity?
)

data class RelatedPlayListEntity(
    val likes: String?,
//    @SerializedName("favorites") val favorites: String // 사용이 중단
    val uploads: String?,
)

data class ChannelResourceStatisticsEntity(
    val viewCount: Long?,
//    @SerializedName("commentCount"), //지원 중단
    val subscriberCount: Long?,
    val hiddenSubscriberCount: Boolean?,
    val videoCount: Long?

)

data class ChannelResourceTopicDetailsEntity(
    val topicCategories: List<String>?
)

data class ChannelResourceStatusEntity(
    val privacyStatus: String?,
    val isLinked: Boolean?,
    val longUploadsStatus: String?,
    val madeForKids: Boolean?,
    val selfDeclaredMadeForKids: Boolean?
)

data class ChannelResourceBrandingSettingsEntity(
    val channel: BrandingSettingsChannelEntity?,
    val watch: BrandingSettingsWatchEntity?
)

data class BrandingSettingsChannelEntity(
    val title: String?,
    val description: String?,
    val keywords: String?,
    val trackingAnalyticsAccountId: String?,
    val unsubscribedTrailer: String?,
    val defaultLanguage: String?,
    val country: String?,
)

data class BrandingSettingsWatchEntity(
    val textColor: String?,
    val backgroundColor: String?,
    val featuredPlaylistId: String?
)

data class ChannelResourceAuditDetailsEntity(
    val overallGoodStanding: Boolean?,
    val communityGuidelinesGoodStanding: Boolean?,
    val copyrightStrikesGoodStanding: Boolean?,
    val contentIdClaimsGoodStanding: Boolean?,
)

data class ChannelResourceContentOwnerDetailsEntity(
    val contentOwner: String?,
    val timeLinked: Date?
)

data class ChannelResourceLocalizationsEntity(
    val title: String?,
    val description: String?
)