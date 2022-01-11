package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.API

import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.Models.PhotosData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FlickrAPIInterface {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&extras=url_h&text&api_key=529c428d57e04dfb8352e85ad47282ea")
    fun getPhotosList(@Query(value = "text") searchTerm: String):Call <PhotosData>
}