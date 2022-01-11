package com.example.alhanoufalghayhab_codingdojo_recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientAPI {

    fun featchRecipData(): Retrofit? {
        val retrofitbuilder = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitbuilder
    }

}