package com.example.favoritetvshows_finalexam.Rooms

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShowTVDao {

    @Insert
    suspend fun addTvShow(tvShow:ShowTV)

    @Delete
    suspend fun deleteTvShow(tvShow:ShowTV)

    @Query("SELECT * FROM TvShow")
    suspend fun getAllShows():List<ShowTV>
}