package com.example.sparta_team_searchyoutubedata.myVideoFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import com.example.sparta_team_searchyoutubedata.room.repository.WatchedListRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val myVideoRepository: MyVideoListRepository,
    private val watchedListRepository: WatchedListRepository
) : ViewModel() {

    val watchedVideosLiveData = watchedListRepository.getAllVideos()
    val myVideoListData = myVideoRepository.getAllVideos()


    fun insertWatchedVideo(watchedVideo: WatchedListEntity) {
        viewModelScope.launch {
            watchedListRepository.insert(watchedVideo)
        }
    }

    fun deleteMyVideo(myVideo: MyVideoListEntity) {
        viewModelScope.launch {
            myVideoRepository.deleteVideo(myVideo.id)
        }
    }

}

class MainViewModelFactory(
    private val myVideoRepository: MyVideoListRepository,
    private val watchedListRepository: WatchedListRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                myVideoRepository = myVideoRepository,
                watchedListRepository = watchedListRepository,
            ) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}