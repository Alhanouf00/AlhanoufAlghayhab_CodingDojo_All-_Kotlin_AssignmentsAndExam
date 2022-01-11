package com.example.alhanoufalghayhab_codingdojo_singletonpractice.API

import com.example.alhanoufalghayhab_codingdojo_singletonpractice.Model.APIData
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    //@Headers("Content-Type: application/json")
    @GET("eur.json")
    fun getCurrency(): Call<APIData>?
}