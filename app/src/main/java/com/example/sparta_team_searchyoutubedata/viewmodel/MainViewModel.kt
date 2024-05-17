package com.example.sparta_team_searchyoutubedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import com.example.sparta_team_searchyoutubedata.room.dao.MyVideoListDao
import com.example.sparta_team_searchyoutubedata.room.dao.WatchedListDao
import com.example.sparta_team_searchyoutubedata.room.database.MyVideoListDatabase
import com.example.sparta_team_searchyoutubedata.room.database.WatchedListDatabase
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import kotlinx.coroutines.launch

class MainViewModel(
    private val youtubeDataRepositoryImpl: YoutubeDataRepositoryImpl,
    watchedListDatabase: WatchedListDatabase,
    myVideoListDatabase: MyVideoListDatabase
):ViewModel(){

    private val watchedListDao = watchedListDatabase.watchedListDao()
    val allVideos: LiveData<List<WatchedListEntity>> = watchedListDao.getAllVideos()

    fun insertWatchedVideo(watchedVideo: WatchedListEntity){
        viewModelScope.launch {
            watchedListDao.insertVideos(watchedVideo)
        }
    }

    private val myVideoListDao = myVideoListDatabase.myVideoListDao()
    val myVideos: LiveData<List<MyVideoListEntity>> = myVideoListDao.getAllVideos()

    fun insertMyVideo(myVideo: MyVideoListEntity){
        viewModelScope.launch {
            myVideoListDao.insertVideos(myVideo)
        }
    }


}


class MainViewModelFactor(
    private val watchedListDatabase: WatchedListDatabase,
    private val myVideoListDatabase: MyVideoListDatabase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return MainViewModel(
            YoutubeDataRepositoryImpl(
                RetrofitClient.youtubeDataRemote
            ),
            watchedListDatabase,
            myVideoListDatabase
        )as T
    }

}