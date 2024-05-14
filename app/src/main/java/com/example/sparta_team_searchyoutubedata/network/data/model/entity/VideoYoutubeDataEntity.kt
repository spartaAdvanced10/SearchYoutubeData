package com.example.sparta_team_searchyoutubedata.network.data.model.entity

import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ContentRating
import java.util.Date

data class VideoYoutubeDataEntity(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val pageInfo: PageInfoEntity?,
    val items: List<VideoResourceEntity>?
)

data class VideoResourceEntity(
    val kind: String?,
    val etag: String?,
    val id: String?,
    val snippet: VideoResourceSnippetEntity?,
    val contentDetails: VideoResourceContentDetailsEntity?,
    val status: VideoResourceStatusEntity?,
    val statistics: VideoResourceStatisticsEntity?,
    val player: VideoResourcePlayerEntity?,
    val topicDetails: VideoResourceTopicDetailsEntity?,
    val recordingDetails: VideoResourceRecordingDetailsEntity?,
    val fileDetails: VideoResourceFileDetailsEntity?,
    val processingDetails: VideoResourceProcessingDetailsEntity?,
    val suggestions: VideoResourceSuggestionsEntity?,
    val liveStreamingDetails: VideoResourceLiveStreamingDetailsEntity?,
    val localizations: LocalizationEntity?
)

data class VideoResourceSnippetEntity(
    val publishedAt: Date?,
    val channelId: String?,
    val title: String?,
    val description: String?,
    val thumbnails: ThumbnailKeyEntity?,
    val channelTitle: String?,
    val tags: List<String>?,
    val categoryId: String?,
    val liveBroadcastContent: String?,
    val defaultLanguage: String?,
    val localized: LocalizedEntity?,
    val defaultAudioLanguage: String?
)


data class VideoResourceContentDetailsEntity(
    val duration: String?,
    val dimension: String?,
    val definition: String?,
    val caption: String?,
    val licensedContent: Boolean?,
    val regionRestriction: RegionRestrictionEntity?,
//    @SerializedName("contentRating") val contentRating: ContentRatingEntity?,
    val projection: String?,
    val hasCustomThumbnail: Boolean?,
    val contentRating: ContentRating?
)

data class RegionRestrictionEntity(
    val allowed: List<String>?,
    val blocked: List<String>?
)


data class VideoResourceStatusEntity(
    val uploadStatus: String?,
    val failureReason: String?,
    val rejectionReason: String?,
    val privacyStatus: String?,
    val publishAt: Date?,
    val license: String?,
    val embeddable: Boolean?,
    val publicStatsViewable: Boolean?,
    val madeForKids: Boolean?,
    val selfDeclaredMadeForKids: Boolean?
)

data class VideoResourceStatisticsEntity(
    val viewCount: ULong?,
    val likeCount: ULong?,
    val commentCount: ULong?
)


data class VideoResourcePlayerEntity(
    val embedHtml: String?,
    val embedHeight: Long?,
    val embedWidth: Long?
)

data class VideoResourceTopicDetailsEntity(
    val topicCategories: List<String>?
)

data class VideoResourceRecordingDetailsEntity(
    val recordingDate: Date?
)

data class VideoResourceFileDetailsEntity(
    val fileName: String?,
    val fileSize: ULong?,
    val fileType: String?,
    val container: String?,
    val videoStreams: List<VideoStreamEntity>?,
    val audioStreams: List<AudioStreamEntity>?,
    val durationMs: ULong?,
    val bitrateBps: ULong?,
    val creationTime: String?
)

data class VideoStreamEntity(
    val widthPixels: UInt?,
    val heightPixels: UInt?,
    val frameRateFps: Double?,
    val aspectRatio: Double?,
    val codec: String?,
    val bitrateBps: ULong?,
    val rotation: String?,
    val vendor: String?
)

data class AudioStreamEntity(
    val channelCount: UInt?,
    val codec: String?,
    val bitrateBps: ULong?,
    val vendor: String?
)

data class VideoResourceProcessingDetailsEntity(
    val processingStatus: String?,
    val processingProgress: ProcessingProgressEntity?,
    val processingFailureReason: String?,
    val fileDetailsAvailability: String?,
    val processingIssuesAvailability: String?,
    val tagSuggestionsAvailability: String?,
    val editorSuggestionsAvailability: String?,
    val thumbnailsAvailability: String?
)

data class ProcessingProgressEntity(
    val partsTotal: ULong?,
    val partsProcessed: ULong?,
    val timeLeftMs: ULong?
)

data class VideoResourceSuggestionsEntity(
    val processingErrors: List<String>?,
    val processingWarnings: List<String>?,
    val processingHints: List<String>?,
    val tagSuggestions: List<TagSuggestionEntity>?,
    val editorSuggestions: List<String>?
)

data class TagSuggestionEntity(
    val tag: String?,
    val categoryRestricts: List<String>?
)

data class VideoResourceLiveStreamingDetailsEntity(
    val actualStartTime: Date?,
    val actualEndTime: Date?,
    val scheduledStartTime: Date?,
    val scheduledEndTime: Date?,
    val concurrentViewers: ULong?,
    val activeLiveChatId: String?
)

data class LocalizationEntity(
    val title: String?,
    val description: String?
)