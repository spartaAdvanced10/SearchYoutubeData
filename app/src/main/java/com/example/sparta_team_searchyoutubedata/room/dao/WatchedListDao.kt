package com.example.sparta_team_searchyoutubedata.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

@Dao
interface WatchedListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: WatchedListEntity)

    @Query("SELECT * FROM watched_list WHERE id = :videoId")
    suspend fun getVideoById(videoId: String): WatchedListEntity

    @Query(value = "DELETE FROM watched_list")
    suspend fun deleteAllVideos()

    @Query("SELECT * FROM watched_list ORDER BY timestamp DESC")
    fun getAllVideos(): LiveData<List<WatchedListEntity>>

    @Delete
    suspend fun delete(video: WatchedListEntity)

    @Query("SELECT * FROM watched_list ORDER BY timestamp ASC LIMIT 1")
    suspend fun getOldestVideo(): WatchedListEntity
}
