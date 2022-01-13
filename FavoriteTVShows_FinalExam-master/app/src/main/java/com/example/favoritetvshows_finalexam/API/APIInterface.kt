package com.example.favoritetvshows_finalexam.API

import com.example.favoritetvshows_finalexam.Models.TVShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("search/shows?q")
    fun getPhotosList(@Query(value = "q") searchTerm: String): Call<TVShow>
}