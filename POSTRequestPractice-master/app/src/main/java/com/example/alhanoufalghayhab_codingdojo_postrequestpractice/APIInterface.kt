package com.example.alhanoufalghayhab_codingdojo_postrequestpractice

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("test/")
    fun getdata(): Call<ArrayList<DataAPIItem>>
    @GET("test/")
    fun getpk():  Call<ArrayList<UpdateData>>
    @POST("test/")
    fun postdata(@Body newOne:DataAPIItem):Call<ArrayList<DataAPIItem>>
    @PUT("test/{pk}")
    fun updatedata(@Path("pk")pk: Int ,@Body updateOne:DataAPIItem):Call<ArrayList<UpdateData>>
    @DELETE("test/{pk}")
    fun deletedata(@Path("pk")pk: Int ):Call<Void>
}