package com.example.sparta_team_searchyoutubedata.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.model.entity.SearchYoutubeDataEntity
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepository
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeDataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState.init())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onSearch(searchKey: String, pageToken: String?) = viewModelScope.launch {
        _uiState.update { prev->
            prev.copy(isLoading = true)
        }
        var searchPageToken:String = ""
        if(pageToken == "prev") searchPageToken = uiState.value.prevPageToken
        else if (pageToken == "next") searchPageToken = uiState.value.nextPageToken

        val searchResult = repository.getSearch(q = searchKey, order = "relevance", maxResults = 5, pageToken = searchPageToken)
        val itemList = getListItem(searchResult)

        itemList?.let { list ->
            _uiState.update { prev ->
                prev.copy(
                    list = list,
                    isLoading = false,
                    nextPageToken = searchResult.nextPageToken ?: "",
                    prevPageToken = searchResult.prevPageToken ?: ""
                )
            }
        }
    }

    private fun getListItem(searchYoutubeDataEntity: SearchYoutubeDataEntity): List<SearchItem>{
        val itemList = searchYoutubeDataEntity.items?.map {
            SearchItem(
                thumbnail = it.snippet?.thumbnails?.default?.url ?: "",
                title = it.snippet?.title ?: "",
                description = it.snippet?.description ?: ""
            )
        }

        return itemList as List<SearchItem>
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val repository = YoutubeDataRepositoryImpl(RetrofitClient.youtubeDataRemote)

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = SearchViewModel(repository) as T
}

