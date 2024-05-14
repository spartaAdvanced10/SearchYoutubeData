package com.example.sparta_team_searchyoutubedata.network.data.model.reponse

import com.google.gson.annotations.SerializedName
import java.util.Date

data class VideoYoutubeDataResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfoResponse?,
    @SerializedName("items") val items: List<VideoResourceResponse>?
)

data class VideoResourceResponse(
    @SerializedName("kind") val kind: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("snippet") val snippet: VideoResourceSnippetResponse?,
    @SerializedName("contentDetails") val contentDetails: VideoResourceContentDetailsResponse?,
    @SerializedName("status") val status: VideoResourceStatusResponse?,
    @SerializedName("statistics") val statistics: VideoResourceStatisticsResponse?,
    @SerializedName("player") val player: VideoResourcePlayerResponse?,
    @SerializedName("topicDetails") val topicDetails: VideoResourceTopicDetailsResponse?,
    @SerializedName("recordingDetails") val recordingDetails: VideoResourceRecordingDetailsResponse?,
    @SerializedName("fileDetails") val fileDetails: VideoResourceFileDetailsResponse?,
    @SerializedName("processingDetails") val processingDetails: VideoResourceProcessingDetailsResponse?,
    @SerializedName("suggestions") val suggestions: VideoResourceSuggestionsResponse?,
    @SerializedName("liveStreamingDetails") val liveStreamingDetails: VideoResourceLiveStreamingDetailsResponse?,
    @SerializedName("localizations") val localizations: LocalizationResponse?
)

data class VideoResourceSnippetResponse(
    @SerializedName("publishedAt") val publishedAt: Date?,
    @SerializedName("channelId") val channelId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnails") val thumbnails: ThumbnailKeyResponse?,
    @SerializedName("channelTitle") val channelTitle: String?,
    @SerializedName("tags") val tags: List<String>?,
    @SerializedName("categoryId") val categoryId: String?,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String?,
    @SerializedName("defaultLanguage") val defaultLanguage: String?,
    @SerializedName("localized") val localized: LocalizedResponse?,
    @SerializedName("defaultAudioLanguage") val defaultAudioLanguage: String?
)


data class VideoResourceContentDetailsResponse(
    @SerializedName("duration") val duration: String?,
    @SerializedName("dimension") val dimension: String?,
    @SerializedName("definition") val definition: String?,
    @SerializedName("caption") val caption: String?,
    @SerializedName("licensedContent") val licensedContent: Boolean?,
    @SerializedName("regionRestriction") val regionRestriction: RegionRestrictionResponse?,
    @SerializedName("contentRating") val contentRating: ContentRating?,
    @SerializedName("projection") val projection: String?,
    @SerializedName("hasCustomThumbnail") val hasCustomThumbnail: Boolean?
)

data class RegionRestrictionResponse(
    @SerializedName("allowed") val allowed: List<String>?,
    @SerializedName("blocked") val blocked: List<String>?
)



data class VideoResourceStatusResponse(
    @SerializedName("uploadStatus") val uploadStatus: String?,
    @SerializedName("failureReason") val failureReason: String?,
    @SerializedName("rejectionReason") val rejectionReason: String?,
    @SerializedName("privacyStatus") val privacyStatus: String?,
    @SerializedName("publishAt") val publishAt: Date?,
    @SerializedName("license") val license: String?,
    @SerializedName("embeddable") val embeddable: Boolean?,
    @SerializedName("publicStatsViewable") val publicStatsViewable: Boolean?,
    @SerializedName("madeForKids") val madeForKids: Boolean?,
    @SerializedName("selfDeclaredMadeForKids") val selfDeclaredMadeForKids: Boolean?
)

data class VideoResourceStatisticsResponse(
    @SerializedName("viewCount") val viewCount: ULong?,
    @SerializedName("likeCount") val likeCount: ULong?,
    @SerializedName("commentCount") val commentCount: ULong?
)


data class VideoResourcePlayerResponse(
    @SerializedName("embedHtml") val embedHtml: String?,
    @SerializedName("embedHeight") val embedHeight: Long?,
    @SerializedName("embedWidth") val embedWidth: Long?
)

data class VideoResourceTopicDetailsResponse(
    @SerializedName("topicCategories") val topicCategories: List<String>?
)

data class VideoResourceRecordingDetailsResponse(
    @SerializedName("recordingDate") val recordingDate: Date?
)

data class VideoResourceFileDetailsResponse(
    @SerializedName("fileName") val fileName: String?,
    @SerializedName("fileSize") val fileSize: ULong?,
    @SerializedName("fileType") val fileType: String?,
    @SerializedName("container") val container: String?,
    @SerializedName("videoStreams") val videoStreams: List<VideoStream>?,
    @SerializedName("audioStreams") val audioStreams: List<AudioStream>?,
    @SerializedName("durationMs") val durationMs: ULong?,
    @SerializedName("bitrateBps") val bitrateBps: ULong?,
    @SerializedName("creationTime") val creationTime: String?
)

data class VideoStream(
    @SerializedName("widthPixels") val widthPixels: UInt?,
    @SerializedName("heightPixels") val heightPixels: UInt?,
    @SerializedName("frameRateFps") val frameRateFps: Double?,
    @SerializedName("aspectRatio") val aspectRatio: Double?,
    @SerializedName("codec") val codec: String?,
    @SerializedName("bitrateBps") val bitrateBps: ULong?,
    @SerializedName("rotation") val rotation: String?,
    @SerializedName("vendor") val vendor: String?
)

data class AudioStream(
    @SerializedName("channelCount") val channelCount: UInt?,
    @SerializedName("codec") val codec: String?,
    @SerializedName("bitrateBps") val bitrateBps: ULong?,
    @SerializedName("vendor") val vendor: String?
)

data class VideoResourceProcessingDetailsResponse(
    @SerializedName("processingStatus") val processingStatus: String?,
    @SerializedName("processingProgress") val processingProgress: ProcessingProgress?,
    @SerializedName("processingFailureReason") val processingFailureReason: String?,
    @SerializedName("fileDetailsAvailability") val fileDetailsAvailability: String?,
    @SerializedName("processingIssuesAvailability") val processingIssuesAvailability: String?,
    @SerializedName("tagSuggestionsAvailability") val tagSuggestionsAvailability: String?,
    @SerializedName("editorSuggestionsAvailability") val editorSuggestionsAvailability: String?,
    @SerializedName("thumbnailsAvailability") val thumbnailsAvailability: String?
)

data class ProcessingProgress(
    @SerializedName("partsTotal") val partsTotal: ULong?,
    @SerializedName("partsProcessed") val partsProcessed: ULong?,
    @SerializedName("timeLeftMs") val timeLeftMs: ULong?
)

data class VideoResourceSuggestionsResponse(
    @SerializedName("processingErrors") val processingErrors: List<String>?,
    @SerializedName("processingWarnings") val processingWarnings: List<String>?,
    @SerializedName("processingHints") val processingHints: List<String>?,
    @SerializedName("tagSuggestions") val tagSuggestions: List<TagSuggestion>?,
    @SerializedName("editorSuggestions") val editorSuggestions: List<String>?
)

data class TagSuggestion(
    @SerializedName("tag") val tag: String?,
    @SerializedName("categoryRestricts") val categoryRestricts: List<String>?
)

data class VideoResourceLiveStreamingDetailsResponse(
    @SerializedName("actualStartTime") val actualStartTime: Date?,
    @SerializedName("actualEndTime") val actualEndTime: Date?,
    @SerializedName("scheduledStartTime") val scheduledStartTime: Date?,
    @SerializedName("scheduledEndTime") val scheduledEndTime: Date?,
    @SerializedName("concurrentViewers") val concurrentViewers: ULong?,
    @SerializedName("activeLiveChatId") val activeLiveChatId: String?
)

data class LocalizationResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?
)


data class ContentRating(
    val acbRating: String?,
    val agcomRating: String?,
    val anatelRating: String?,
    val bbfcRating: String?,
    val bfvcRating: String?,
    val bmukkRating: String?,
    val catvRating: String?,
    val catvfrRating: String?,
    val cbfcRating: String?,
    val cccRating: String?,
    val cceRating: String?,
    val chfilmRating: String?,
    val chvrsRating: String?,
    val cicfRating: String?,
    val cnaRating: String?,
    val cncRating: String?,
    val csaRating: String?,
    val cscfRating: String?,
    val czfilmRating: String?,
    val djctqRating: String?,
    val djctqRatingReasons: List<String>?,
    val ecbmctRating: String?,
    val eefilmRating: String?,
    val egfilmRating: String?,
    val eirinRating: String?,
    val fcbmRating: String?,
    val fcoRating: String?,
    val fmocRating: String?,
    val fpbRating: String?,
    val fpbRatingReasons: List<String>?,
    val fskRating: String?,
    val grfilmRating: String?,
    val icaaRating: String?,
    val ifcoRating: String?,
    val ilfilmRating: String?,
    val incaaRating: String?,
    val kfcbRating: String?,
    val kijkwijzerRating: String?,
    val kmrbRating: String?,
    val lsfRating: String?,
    val mccaaRating: String?,
    val mccypRating: String?,
    val mcstRating: String?,
    val mdaRating: String?,
    val medietilsynetRating: String?,
    val mekuRating: String?,
    val mibacRating: String?,
    val mocRating: String?,
    val moctwRating: String?,
    val mpaaRating: String?,
    val mpaatRating: String?,
    val mtrcbRating: String?,
    val nbcRating: String?,
    val nbcplRating: String?,
    val nfrcRating: String?,
    val nfvcbRating: String?,
    val nkclvRating: String?,
    val oflcRating: String?,
    val pefilmRating: String?,
    val rcnofRating: String?,
    val resorteviolenciaRating: String?,
    val rtcRating: String?,
    val rteRating: String?,
    val russiaRating: String?,
    val skfilmRating: String?,
    val smaisRating: String?,
    val smsaRating: String?,
    val tvpgRating: String?,
    val ytRating: String?
)
