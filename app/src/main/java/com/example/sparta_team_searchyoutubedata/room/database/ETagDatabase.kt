package com.example.sparta_team_searchyoutubedata.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sparta_team_searchyoutubedata.room.dao.ETagDao
import com.example.sparta_team_searchyoutubedata.room.entity.ETagEntity

@Database(entities = [ETagEntity::class], version = 1, exportSchema = false)
abstract class ETagDatabase: RoomDatabase() {
    abstract fun eTagDao():ETagDao
}