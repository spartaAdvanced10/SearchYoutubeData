package com.example.sparta_team_searchyoutubedata.room.repository

import androidx.lifecycle.LiveData
import com.example.sparta_team_searchyoutubedata.room.dao.MyDao
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

class MyRoomRepository(private val dao: MyDao) {
    //my_video_list
    fun getAllVideosWithMyList(): LiveData<List<MyVideoListEntity>> {
        return dao.getAllVideosWithMyList()
    }

    suspend fun insert(myVideoList: MyVideoListEntity) {
        dao.insertVideos(myVideoList)
    }

    suspend fun deleteVideo(videoById: String) {
        dao.deleteVideos(videoById)
    }

    suspend fun getVideoById(videoById: String): MyVideoListEntity? {
        return dao.getVideoByIdWithMyList(videoById)
    }

    //watched_list
    fun getAllVideosWithWatchedList(): LiveData<List<WatchedListEntity>> {
        return dao.getAllVideosWithWatchedList()
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

    suspend fun getOldestVideo(): WatchedListEntity {
        return dao.getOldestVideo()
    }

}