package com.example.sparta_team_searchyoutubedata.room.repository

import androidx.lifecycle.LiveData
import com.example.sparta_team_searchyoutubedata.room.dao.WatchedListDao
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

class WatchedListRepository(private val dao: WatchedListDao) {

    fun getAllVideos(): LiveData<List<WatchedListEntity>> {
        return dao.getAllVideos()
    }

    suspend fun insert(watchedList: WatchedListEntity) {
        dao.insertVideo(watchedList)
    }

    suspend fun deleteAllVideos() {
        dao.deleteAllVideos()
    }

    suspend fun delete(video: WatchedListEntity){
        dao.delete(video)
    }

    suspend fun getOldestVideo(): WatchedListEntity{
        return dao.getOldestVideo()
    }

}