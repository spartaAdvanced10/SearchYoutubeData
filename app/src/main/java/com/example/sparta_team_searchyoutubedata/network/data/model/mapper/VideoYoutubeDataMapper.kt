package com.example.sparta_team_searchyoutubedata.network.data.model.mapper

import com.example.sparta_team_searchyoutubedata.network.data.model.entity.AudioStreamEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.LocalizationEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.ProcessingProgressEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.RegionRestrictionEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.TagSuggestionEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceContentDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceFileDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceLiveStreamingDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourcePlayerEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceProcessingDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceRecordingDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceSnippetEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceStatisticsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceStatusEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceSuggestionsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoResourceTopicDetailsEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoStreamEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.VideoYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.AudioStream
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.LocalizationResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.ProcessingProgress
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.RegionRestrictionResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.TagSuggestion
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceContentDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceFileDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceLiveStreamingDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourcePlayerResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceProcessingDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceRecordingDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceSnippetResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceStatisticsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceStatusResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceSuggestionsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoResourceTopicDetailsResponse
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoStream
import com.example.sparta_team_searchyoutubedata.network.data.model.reponse.VideoYoutubeDataResponse

fun VideoYoutubeDataResponse.toEntity() = VideoYoutubeDataEntity(
    kind = kind,
    etag = etag,
    nextPageToken = nextPageToken,
    prevPageToken = prevPageToken,
    pageInfo = pageInfo?.toEntity(),
    items = items?.map {
        it.toEntity()
    }
)

fun VideoResourceResponse.toEntity() = VideoResourceEntity(
    kind = kind,
    etag = etag,
    id = id,
    snippet = snippet?.toEntity(),
    contentDetails = contentDetails?.toEntity(),
    status = status?.toEntity(),
    statistics = statistics?.toEntity(),
    player = player?.toEntity(),
    topicDetails = topicDetails?.toEntity(),
    recordingDetails = recordingDetails?.toEntity(),
    fileDetails = fileDetails?.toEntity(),
    processingDetails = processingDetails?.toEntity(),
    suggestions = suggestions?.toEntity(),
    liveStreamingDetails = liveStreamingDetails?.toEntity(),
    localizations = localizations?.toEntity()
)

fun VideoResourceSnippetResponse.toEntity() = VideoResourceSnippetEntity(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    thumbnails = thumbnails?.toEntity(),
    channelTitle = channelTitle,
    tags = tags,
    categoryId = categoryId,
    liveBroadcastContent = liveBroadcastContent,
    defaultLanguage = defaultLanguage,
    localized = localized?.toEntity(),
    defaultAudioLanguage = defaultAudioLanguage
)


fun VideoResourceContentDetailsResponse.toEntity() = VideoResourceContentDetailsEntity(
    duration = duration,
    dimension = dimension,
    definition = definition,
    caption = caption,
    licensedContent = licensedContent,
    regionRestriction = regionRestriction?.toEntity(),
//    @SerializedName("contentRating")  contentRating: ContentRatingEntity?,
    projection = projection,
    hasCustomThumbnail = hasCustomThumbnail,
    contentRating = contentRating
)

fun RegionRestrictionResponse.toEntity() = RegionRestrictionEntity(
    allowed = allowed,
    blocked = blocked
)


fun VideoResourceStatusResponse.toEntity() = VideoResourceStatusEntity(
    uploadStatus = uploadStatus,
    failureReason = failureReason,
    rejectionReason = rejectionReason,
    privacyStatus = privacyStatus,
    publishAt = publishAt,
    license = license,
    embeddable = embeddable,
    publicStatsViewable = publicStatsViewable,
    madeForKids = madeForKids,
    selfDeclaredMadeForKids = selfDeclaredMadeForKids
)

fun VideoResourceStatisticsResponse.toEntity() = VideoResourceStatisticsEntity(
    viewCount = viewCount,
    likeCount = likeCount,
    commentCount = commentCount
)


fun VideoResourcePlayerResponse.toEntity() = VideoResourcePlayerEntity(
    embedHtml = embedHtml,
    embedHeight = embedHeight,
    embedWidth = embedWidth
)

fun VideoResourceTopicDetailsResponse.toEntity() = VideoResourceTopicDetailsEntity(
    topicCategories = topicCategories
)

fun VideoResourceRecordingDetailsResponse.toEntity() = VideoResourceRecordingDetailsEntity(
    recordingDate = recordingDate
)

fun VideoResourceFileDetailsResponse.toEntity() = VideoResourceFileDetailsEntity(
    fileName = fileName,
    fileSize = fileSize,
    fileType = fileType,
    container = container,
    videoStreams = videoStreams?.map {
        it.toEntity()
    },
    audioStreams = audioStreams?.map {
        it.toEntity()
    },
    durationMs = durationMs,
    bitrateBps = bitrateBps,
    creationTime = creationTime
)

fun VideoStream.toEntity() = VideoStreamEntity(
    widthPixels = widthPixels,
    heightPixels = heightPixels,
    frameRateFps = frameRateFps,
    aspectRatio = aspectRatio,
    codec = codec,
    bitrateBps = bitrateBps,
    rotation = rotation,
    vendor = vendor
)

fun AudioStream.toEntity() = AudioStreamEntity(
    channelCount = channelCount,
    codec = codec,
    bitrateBps = bitrateBps,
    vendor = vendor
)

fun VideoResourceProcessingDetailsResponse.toEntity() = VideoResourceProcessingDetailsEntity(
    processingStatus = processingStatus,
    processingProgress = processingProgress?.toEntity(),
    processingFailureReason = processingFailureReason,
    fileDetailsAvailability = fileDetailsAvailability,
    processingIssuesAvailability = processingIssuesAvailability,
    tagSuggestionsAvailability = tagSuggestionsAvailability,
    editorSuggestionsAvailability = editorSuggestionsAvailability,
    thumbnailsAvailability = thumbnailsAvailability
)

fun ProcessingProgress.toEntity() = ProcessingProgressEntity(
    partsTotal = partsTotal,
    partsProcessed = partsProcessed,
    timeLeftMs = timeLeftMs
)

fun VideoResourceSuggestionsResponse.toEntity() = VideoResourceSuggestionsEntity(
    processingErrors = processingErrors,
    processingWarnings = processingWarnings,
    processingHints = processingHints,
    tagSuggestions = tagSuggestions?.map {
        it.toEntity()
    },
    editorSuggestions = editorSuggestions
)

fun TagSuggestion.toEntity() = TagSuggestionEntity(
    tag = tag,
    categoryRestricts = categoryRestricts
)

fun VideoResourceLiveStreamingDetailsResponse.toEntity() = VideoResourceLiveStreamingDetailsEntity(
    actualStartTime = actualStartTime,
    actualEndTime = actualEndTime,
    scheduledStartTime = scheduledEndTime,
    scheduledEndTime = scheduledEndTime,
    activeLiveChatId = activeLiveChatId,
    concurrentViewers = concurrentViewers
)

fun LocalizationResponse.toEntity() = LocalizationEntity(
    title = title,
    description = description
)