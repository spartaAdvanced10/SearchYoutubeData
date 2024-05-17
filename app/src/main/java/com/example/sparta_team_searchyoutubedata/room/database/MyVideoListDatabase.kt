package com.example.sparta_team_searchyoutubedata.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sparta_team_searchyoutubedata.room.dao.MyVideoListDao
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity


@Database(entities = [MyVideoListEntity::class], version = 1)
abstract class MyVideoListDatabase: RoomDatabase() {
    abstract fun myVideoListDao(): MyVideoListDao

    companion object
    @Volatile private var INSTANCE: MyVideoListDatabase? = null

    fun getDatabase(context: Context): MyVideoListDatabase {
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MyVideoListDatabase::class.java,
                "my_video_list_database"
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            instance
        }
    }
}