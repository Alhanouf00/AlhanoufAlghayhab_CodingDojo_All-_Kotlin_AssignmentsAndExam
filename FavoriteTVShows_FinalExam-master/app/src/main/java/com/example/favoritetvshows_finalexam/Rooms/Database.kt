package com.example.favoritetvshows_finalexam.Rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShowTV::class], version = 1)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao():ShowTVDao

    companion object {
        @Volatile
        private var INSTANCE:TvShowDatabase? = null
        fun getDatabase(context: Context): TvShowDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowDatabase::class.java,
                    "TvShow"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

