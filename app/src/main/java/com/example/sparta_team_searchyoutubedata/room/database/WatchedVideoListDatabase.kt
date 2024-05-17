package com.example.sparta_team_searchyoutubedata.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sparta_team_searchyoutubedata.room.dao.WatchedListDao
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

@Database(entities = [WatchedListEntity::class], version = 1)
abstract class WatchedListDatabase: RoomDatabase(){
    abstract fun watchedListDao(): WatchedListDao

    companion object{
        @Volatile private var INSTANCE: WatchedListDatabase? = null

        fun getDataBase(context: Context): WatchedListDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchedListDatabase::class.java,
                    "watched_list_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE =instance
                instance
            }
        }
    }
}