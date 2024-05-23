package com.example.sparta_team_searchyoutubedata.myVideoFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import com.example.sparta_team_searchyoutubedata.room.repository.MyRoomRepository
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import com.example.sparta_team_searchyoutubedata.room.repository.WatchedListRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val myRoomRepository: MyRoomRepository
) : ViewModel() {

    val watchedVideosLiveData = myRoomRepository.getAllVideosWithWatchedList()
    val myVideoListData = myRoomRepository.getAllVideosWithMyList()


    fun insertWatchedVideo(watchedVideo: WatchedListEntity) {
        viewModelScope.launch {
            myRoomRepository.insert(watchedVideo)
        }
    }

    fun deleteMyVideo(myVideo: MyVideoListEntity) {
        viewModelScope.launch {
            myRoomRepository.deleteVideo(myVideo.id)
        }
    }

}

class MainViewModelFactory(
    private val myRoomRepository: MyRoomRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                myRoomRepository = myRoomRepository
            ) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}