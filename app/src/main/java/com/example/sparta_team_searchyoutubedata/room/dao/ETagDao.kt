package com.example.sparta_team_searchyoutubedata.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sparta_team_searchyoutubedata.room.entity.ETagEntity

@Dao
interface ETagDao {
    @Query("SELECT * FROM etag_cache WHERE url = :url")
    fun getETagUrl(url:String): ETagEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateETag(eTagEntity: ETagEntity)
}