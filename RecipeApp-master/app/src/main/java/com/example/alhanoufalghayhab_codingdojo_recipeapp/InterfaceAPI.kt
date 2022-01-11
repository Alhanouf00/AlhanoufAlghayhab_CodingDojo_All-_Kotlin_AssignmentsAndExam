package com.example.alhanoufalghayhab_codingdojo_recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface InterfaceAPI {

    @GET("recipes/")
    fun getRecipdata(): Call<ArrayList<RecipeAPIDataItem>>

    @POST("recipes/")
    fun postRecipdata(@Body newRecipe:RecipeAPIDataItem):Call<ArrayList<RecipeAPIDataItem>>
    @PUT("recipes/")
    fun editeRecpedata()


}