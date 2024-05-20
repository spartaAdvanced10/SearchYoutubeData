package com.example.sparta_team_searchyoutubedata.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sparta_team_searchyoutubedata.room.dao.MyVideoListDao
import com.example.sparta_team_searchyoutubedata.room.entity.MyVideoListEntity


@Database(entities = [MyVideoListEntity::class], version = 1)
abstract class MyVideoListDatabase : RoomDatabase() {
    abstract fun myVideoListDao(): MyVideoListDao

    companion object {

        @Volatile
        private var instance: MyVideoListDatabase? = null

        fun getMyVideoDatabase(context: Context): MyVideoListDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyVideoListDatabase::class.java,
                    "my_video_list_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                this.instance = instance
                instance
            }
        }
    }
}