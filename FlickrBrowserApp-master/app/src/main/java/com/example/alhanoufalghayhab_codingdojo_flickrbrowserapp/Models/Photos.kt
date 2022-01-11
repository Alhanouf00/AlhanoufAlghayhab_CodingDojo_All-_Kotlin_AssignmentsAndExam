package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.Models

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)