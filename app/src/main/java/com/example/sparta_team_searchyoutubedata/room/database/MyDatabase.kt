package com.example.sparta_team_searchyoutubedata.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sparta_team_searchyoutubedata.room.dao.MyDao
import com.example.sparta_team_searchyoutubedata.room.entity.ETagEntity
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity

@Database(
    entities = [MyVideoListEntity::class, WatchedListEntity::class, ETagEntity::class],
    version = 1
)
abstract class MyDatabase:RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        private var instance: MyDatabase? = null

        fun getMyDatabase(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                this.instance = instance
                instance
            }
        }
    }
}