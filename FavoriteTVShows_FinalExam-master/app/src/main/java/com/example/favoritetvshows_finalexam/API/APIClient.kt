package com.example.favoritetvshows_finalexam.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    fun featchData(): Retrofit? {
        val retrofitbuilder = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitbuilder
    }

}