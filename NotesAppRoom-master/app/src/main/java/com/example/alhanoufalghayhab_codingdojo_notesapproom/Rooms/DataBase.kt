package com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EntityNote::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao():DaoNote

    companion object {
        @Volatile
        private var INSTANCE:NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "Notes"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    }



