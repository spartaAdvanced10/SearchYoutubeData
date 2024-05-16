package com.example.sparta_team_searchyoutubedata.network.data.model.mapper

import com.example.sparta_team_searchyoutubedata.network.data.model.entity.BrandingSettingsChannelEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.BrandingSettingsWatchEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceAuditDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceBrandingSettingsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceContentDetailEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceContentOwnerDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceLocalizationsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceSnippetEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceStatisticsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceStatusEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelResourceTopicDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ChannelYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.LocalizedEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.RelatedPlayListEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.BrandingSettingsChannelResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.BrandingSettingsWatchResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceAuditDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceBrandingSettingsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceContentDetailResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceContentOwnerDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceLocalizationsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceSnippetResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceStatisticsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceStatusResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelResourceTopicDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ChannelYoutubeDataResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.LocalizedResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.RelatedPlayListResponse

fun ChannelYoutubeDataResponse.toEntity(): ChannelYoutubeDataEntity {
    return ChannelYoutubeDataEntity(
        kind = kind,
        etag = etag,
        nextPageToken = nextPageToken,
        prevPageToken = prevPageToken,
        pageInfo = pageInfo?.toEntity(),
        items = items?.map { it.toEntity() }
    )
}

fun ChannelResourceResponse.toEntity(): ChannelResourceEntity {
    return ChannelResourceEntity(
        kind = kind,
        etag = etag,
        id = id,
        snippet = snippet?.toEntity(),
        contentDetails = contentDetails?.toEntity(),
        statistics = statistics?.toEntity(),
        topicDetails = topicDetails?.toEntity(),
        status = status?.toEntity(),
        brandingSettings = brandingSettings?.toEntity(),
        auditDetails = auditDetails?.toEntity(),
        contentOwnerDetails = contentOwnerDetails?.toEntity(),
        localizations = localizations?.toEntity()
    )
}

fun ChannelResourceSnippetResponse.toEntity(): ChannelResourceSnippetEntity {
    return ChannelResourceSnippetEntity(
        title = title,
        description = description,
        customUrl = customUrl,
        publishedAt = publishedAt,
        thumbnails = thumbnails?.toEntity(),
        defaultLanguage = defaultLanguage,
        localized = localized?.toEntity(),
        country = country
    )
}

fun ChannelResourceContentDetailResponse.toEntity(): ChannelResourceContentDetailEntity {
    return ChannelResourceContentDetailEntity(
        relatedPlayList = relatedPlayList?.toEntity()
    )
}

fun RelatedPlayListResponse.toEntity(): RelatedPlayListEntity {
    return RelatedPlayListEntity(
        likes = likes,
        uploads = uploads
    )
}

fun ChannelResourceStatisticsResponse.toEntity(): ChannelResourceStatisticsEntity {
    return ChannelResourceStatisticsEntity(
        viewCount = viewCount,
        subscriberCount = subscriberCount,
        hiddenSubscriberCount = hiddenSubscriberCount,
        videoCount = videoCount
    )
}

fun ChannelResourceTopicDetailsResponse.toEntity(): ChannelResourceTopicDetailsEntity {
    return ChannelResourceTopicDetailsEntity(
        topicCategories = topicCategories
    )
}

fun ChannelResourceStatusResponse.toEntity(): ChannelResourceStatusEntity {
    return ChannelResourceStatusEntity(
        privacyStatus = privacyStatus,
        isLinked = isLinked,
        longUploadsStatus = longUploadsStatus,
        madeForKids = madeForKids,
        selfDeclaredMadeForKids = selfDeclaredMadeForKids
    )
}

fun ChannelResourceBrandingSettingsResponse.toEntity(): ChannelResourceBrandingSettingsEntity {
    return ChannelResourceBrandingSettingsEntity(
        channel = channel?.toEntity(),
        watch = watch?.toEntity()
    )
}

fun BrandingSettingsChannelResponse.toEntity(): BrandingSettingsChannelEntity {
    return BrandingSettingsChannelEntity(
        title = title,
        description = description,
        keywords = keywords,
        trackingAnalyticsAccountId = trackingAnalyticsAccountId,
        unsubscribedTrailer = unsubscribedTrailer,
        defaultLanguage = defaultLanguage,
        country = country
    )
}

fun BrandingSettingsWatchResponse.toEntity(): BrandingSettingsWatchEntity {
    return BrandingSettingsWatchEntity(
        textColor = textColor,
        backgroundColor = backgroundColor,
        featuredPlaylistId = featuredPlaylistId
    )
}

fun ChannelResourceAuditDetailsResponse.toEntity(): ChannelResourceAuditDetailsEntity {
    return ChannelResourceAuditDetailsEntity(
        overallGoodStanding = overallGoodStanding,
        communityGuidelinesGoodStanding = communityGuidelinesGoodStanding,
        copyrightStrikesGoodStanding = copyrightStrikesGoodStanding,
        contentIdClaimsGoodStanding = contentIdClaimsGoodStanding
    )
}

fun ChannelResourceContentOwnerDetailsResponse.toEntity(): ChannelResourceContentOwnerDetailsEntity {
    return ChannelResourceContentOwnerDetailsEntity(
        contentOwner = contentOwner,
        timeLinked = timeLinked
    )
}

fun ChannelResourceLocalizationsResponse.toEntity(): ChannelResourceLocalizationsEntity {
    return ChannelResourceLocalizationsEntity(
        title = title,
        description = description
    )
}
fun LocalizedResponse.toEntity(): LocalizedEntity {
    return LocalizedEntity(
        title = title,
        description = description
    )
}
