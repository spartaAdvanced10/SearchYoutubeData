package com.example.sparta_team_searchyoutubedata.search

data class SearchUiState(
    val list: List<SearchItem>,
    val isLoading: Boolean
) {
    companion object {
        fun init() = SearchUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}