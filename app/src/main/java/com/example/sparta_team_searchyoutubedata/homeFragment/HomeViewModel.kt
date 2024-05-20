package com.example.sparta_team_searchyoutubedata.homeFragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepository
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import com.example.sparta_team_searchyoutubedata.room.dao.WatchedListDao
import com.example.sparta_team_searchyoutubedata.room.database.WatchedListDatabase
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository:YoutubeDataRepository,
    private val watchedRepository: WatchedListDao
) : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState.init())
    val uiState:StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadPopular() = viewModelScope.launch {
        val itemList = repository.getVideos("snippet", "mostPopular", 5, "0").items?.map{
            HomeItemModel(
                title = it.snippet?.title ?: "",
                thumbnails = it.snippet?.thumbnails?.default?.url ?: "",
                description = it.snippet?.description ?: "",
                channelID = it.snippet?.channelId ?: "",
                isLiked = false
            )
        }
        itemList?.let { list ->
            _uiState.update { prev ->
                prev.copy(
                    list = list
                )
            }
        }
    }
    fun loadCategory(id : String) = viewModelScope.launch {
        val categoryList = repository.getVideos("snippet", "mostPopular", 5, id).items?.map{
            HomeItemModel(
                title = it.snippet?.title ?: "",
                thumbnails = it.snippet?.thumbnails?.default?.url ?: "",
                description = it.snippet?.description ?: "",
                channelID = it.snippet?.channelId ?: "",
                isLiked = false
            )
        }
        val channelIdlList =  repository.getVideos("snippet", "mostPopular", 5, id).items?.map{
            it.snippet?.channelId ?: ""
        }
        if (channelIdlList != null) {
            loadChannel(channelIdlList)
        }
        categoryList?.let { list ->
            _uiState.update { prev ->
                prev.copy(
                    listC = list
                )
            }
        }
    }
    fun loadChannel(id: List<String>) = viewModelScope.launch {
        var channelList : MutableList<HomeItemModel> = mutableListOf()
        for(i in 0 .. 4){
            repository.getChannel("snippet", 5, id[i]).items?.forEach {
                channelList.add(HomeItemModel(
                    title = it.snippet?.title ?: "",
                    thumbnails = it.snippet?.thumbnails?.default?.url ?: "",
                    description = it.snippet?.description ?: "",
                    channelID = id[i],
                    isLiked = false
                ))
            }

        }
        channelList?.let { list ->
            _uiState.update { prev ->
                prev.copy(
                    listChannel = list
                )
            }
        }
    }
    fun saveWatchedVideo(watchedVideo: WatchedListEntity){
        viewModelScope.launch {
            watchedRepository.insertVideo(watchedVideo)
        }
    }
}
class HomeViewModelFactory(context: Context) : ViewModelProvider.Factory{
    private val repository = YoutubeDataRepositoryImpl(RetrofitClient.youtubeDataRemote)
    private val watchedRepository= WatchedListDatabase.getDataBase(context).watchedListDao()

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) : T = HomeViewModel(repository, watchedRepository) as T
}