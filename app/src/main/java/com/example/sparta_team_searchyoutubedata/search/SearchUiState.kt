package com.example.sparta_team_searchyoutubedata.search

data class SearchUiState(
    val list: List<SearchItem>,
    val isLoading: Boolean,
    val nextPageToken:String,
    val prevPageToken:String,
    val resultPerPage: Int = 0,
    val nowPage: Int = 0
) {
    companion object {
        fun init() = SearchUiState(
            list = emptyList(),
            isLoading = false,
            nextPageToken = "",
            prevPageToken = ""
        )
    }
}