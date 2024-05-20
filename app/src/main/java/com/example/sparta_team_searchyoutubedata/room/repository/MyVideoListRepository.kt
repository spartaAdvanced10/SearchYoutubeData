package com.example.sparta_team_searchyoutubedata.room.repository

import androidx.lifecycle.LiveData
import com.example.sparta_team_searchyoutubedata.room.dao.MyVideoListDao
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity

class MyVideoListRepository(private val dao: MyVideoListDao) {

    fun getAllVideos(): LiveData<List<MyVideoListEntity>> {
        return dao.getAllVideos()
    }

    suspend fun insert(myVideoList: MyVideoListEntity) {
        dao.insertVideos(myVideoList)
    }

    suspend fun deleteVideo(videoById: String) {
        dao.deleteVideos(videoById)
    }

    suspend fun getVideoById(videoById: String): MyVideoListEntity? {
        return dao.getVideoById(videoById)
    }
}