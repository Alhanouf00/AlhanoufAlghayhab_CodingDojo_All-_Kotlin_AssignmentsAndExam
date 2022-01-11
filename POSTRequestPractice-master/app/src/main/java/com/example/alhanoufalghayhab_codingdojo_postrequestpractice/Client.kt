package com.example.alhanoufalghayhab_codingdojo_postrequestpractice


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client() {

    fun featchData(): Retrofit? {
          val retrofitbuilder = Retrofit.Builder()
             .baseUrl("https://dojo-recipes.herokuapp.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
        return retrofitbuilder
    }
}