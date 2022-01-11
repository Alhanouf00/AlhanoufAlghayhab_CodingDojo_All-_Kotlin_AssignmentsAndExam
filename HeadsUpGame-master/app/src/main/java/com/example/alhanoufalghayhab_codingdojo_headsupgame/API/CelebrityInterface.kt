package com.example.alhanoufalghayhab_codingdojo_headsupgame.API

import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.CelebrityListData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.ListDataWithPK
import retrofit2.Call
import retrofit2.http.*

interface CelebrityInterface {
    @GET("celebrities")
    fun getdata(): Call<ArrayList<CelebrityListData>>
    @GET("celebrities/{pk}")
    fun getspicifdata(@Path("pk")pk:Int,@Query("name")name:String): Call<ArrayList<ListDataWithPK>>
    @GET("celebrities/")
    fun getdataWithPk2(): Call<ArrayList<ListDataWithPK>>
    @POST("celebrities/")
    fun postdata(@Body newCele: CelebrityListData):Call<ArrayList<CelebrityListData>>
    @PUT("celebrities/{pk}")
    fun updatedata(@Path("pk")pk: Int ,@Body updateOne:CelebrityListData):Call<ArrayList<CelebrityListData>>
    @DELETE("celebrities/{pk}")
    fun deletedata(@Path("pk")pk: Int ): Call<Void>
}