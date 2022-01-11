package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrClient {
    fun featchPhoto(): Retrofit? {
        val retrofitbuilder = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitbuilder
    }

}