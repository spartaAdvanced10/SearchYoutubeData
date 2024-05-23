package com.example.sparta_team_searchyoutubedata.videoDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.repository.MyRoomRepository
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoDetailViewModel(
    private val videoDetailItem: VideoDetailItem,
    private val repository: MyRoomRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(videoDetailItem)
    val uiState: StateFlow<VideoDetailItem> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val existingVideo = repository.getVideoById(videoById = videoDetailItem.thumbnail)
            if(existingVideo != null) {
                _uiState.value = videoDetailItem.copy(isLiked = existingVideo.isLiked)
            }
        }
    }

    fun onLiked(){
        viewModelScope.launch{
            val existingVideo = repository.getVideoById(videoById = videoDetailItem.thumbnail)
            if (existingVideo == null) {
                repository.insert(
                    MyVideoListEntity(
                        id = videoDetailItem.thumbnail,
                        thumbnailUrl = videoDetailItem.thumbnail,
                        title = videoDetailItem.title,
                        description = videoDetailItem.description,
                        isLiked = true
                    )
                )
                _uiState.update { it.copy(isLiked = true) }
            }else {
                repository.deleteVideo(videoDetailItem.thumbnail)
                _uiState.update { it.copy(isLiked = false) }
            }

            val videos = repository.getAllVideosWithMyList().value
            Log.d("MyVideoList", "$videos, ${_uiState.value.isLiked}")
        }
    }
}

class VideoDetailViewModelFactory(
    private val videoDetailItem: VideoDetailItem,
    private val repository: MyRoomRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = VideoDetailViewModel(videoDetailItem, repository) as T
}