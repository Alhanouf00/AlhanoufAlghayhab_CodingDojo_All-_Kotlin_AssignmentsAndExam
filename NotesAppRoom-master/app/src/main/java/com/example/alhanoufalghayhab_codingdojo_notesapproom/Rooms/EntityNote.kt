package com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class EntityNote(
    @PrimaryKey(autoGenerate = true)val pk: Int,
    val note:String
)