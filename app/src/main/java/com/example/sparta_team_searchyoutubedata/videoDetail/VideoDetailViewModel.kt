package com.example.sparta_team_searchyoutubedata.videoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoDetailViewModel(
    private val videoDetailItem: VideoDetailItem
): ViewModel() {
    private val _uiState = MutableStateFlow(videoDetailItem)
    val uiState: StateFlow<VideoDetailItem> = _uiState.asStateFlow()

    fun onLiked(){
        _uiState.update { prev ->
            prev.copy(
                isLiked = !prev.isLiked
            )
        }
    }
}

class VideoDetailViewModelFactory(
    private val videoDetailItem: VideoDetailItem
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = VideoDetailViewModel(videoDetailItem) as T
}