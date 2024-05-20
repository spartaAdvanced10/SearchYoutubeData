package com.example.sparta_team_searchyoutubedata.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity


// 아직 미구현,
// 추후 좋아요 처리 될 때 구현 완료 예정
@Dao
interface MyVideoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(video: MyVideoListEntity)

    @Query("SELECT * FROM my_video_list WHERE id = :videoId")
    suspend fun getVideoById(videoId: String): MyVideoListEntity

    @Query("DELETE FROM my_video_list")
    suspend fun deleteAllVideos()

    @Query("DELETE FROM my_video_list WHERE id = :videoId")
    suspend fun deleteVideos(videoId: String)

    @Query("SELECT * FROM my_video_list")
    fun getAllVideos(): LiveData<List<MyVideoListEntity>>
}