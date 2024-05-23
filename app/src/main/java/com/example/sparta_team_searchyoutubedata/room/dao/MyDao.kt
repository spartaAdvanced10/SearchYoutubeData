package com.example.sparta_team_searchyoutubedata.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

@Dao
interface MyDao {
    //watchedList
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: WatchedListEntity)

    @Query("SELECT * FROM watched_list WHERE id = :videoId")
    suspend fun getVideoByIdWithWatchedList(videoId: String): WatchedListEntity

    @Query(value = "DELETE FROM watched_list")
    suspend fun deleteAllVideos()

    @Query("SELECT * FROM watched_list ORDER BY timestamp DESC")
    fun getAllVideosWithWatchedList(): LiveData<List<WatchedListEntity>>

    @Delete
    suspend fun delete(video: WatchedListEntity)

    @Query("SELECT * FROM watched_list ORDER BY timestamp ASC LIMIT 1")
    suspend fun getOldestVideo(): WatchedListEntity


    //myVideoList
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(video: MyVideoListEntity)

    @Query("SELECT * FROM my_video_list WHERE id = :videoId")
    suspend fun getVideoByIdWithMyList(videoId: String): MyVideoListEntity

    @Query("DELETE FROM my_video_list")
    suspend fun deleteAllVideosWithMyList()

    @Query("DELETE FROM my_video_list WHERE id = :videoId")
    suspend fun deleteVideos(videoId: String)

    @Query("SELECT * FROM my_video_list")
    fun getAllVideosWithMyList(): LiveData<List<MyVideoListEntity>>
}