package com.example.favoritetvshows_finalexam.Rooms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShow")
data class ShowTV (@PrimaryKey(autoGenerate = true)val id: Int,
                   val name:String, val language:String, val imageshow:String, val summary:String )
