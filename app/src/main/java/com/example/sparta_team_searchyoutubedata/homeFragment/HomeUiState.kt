package com.example.sparta_team_searchyoutubedata.homeFragment

data class HomeUiState(
    val list:List<HomeItemModel>,
    val listC:List<HomeItemModel>,
    val listChannel: List<HomeItemModel>,
    val isLoading:Boolean
){
    companion object{
        fun init() = HomeUiState(
            list = emptyList(),
            listC = emptyList(),
            listChannel = emptyList(),
            isLoading = false
        )
    }
}