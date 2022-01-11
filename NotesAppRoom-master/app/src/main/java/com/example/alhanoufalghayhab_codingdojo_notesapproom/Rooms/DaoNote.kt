package com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms

import androidx.room.*


@Dao
interface DaoNote {

    @Insert
    suspend fun addNote(note:EntityNote)
    @Update
    suspend fun updateNote(note:EntityNote)
    @Delete
    suspend fun deleteNote(note:EntityNote)
    @Query("SELECT * FROM Notes")
    suspend fun getAllNotes():List<EntityNote>
}