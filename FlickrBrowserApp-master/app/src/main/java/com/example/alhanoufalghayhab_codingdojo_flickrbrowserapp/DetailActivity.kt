package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    lateinit var Binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        showDetail()
    }


    fun showDetail()
    {
        Log.d("TAG", "showDetail")

        val showImage = intent.getStringExtra("imagedetail")
        val showtitle = intent.getStringExtra("title")

        Picasso.get().load(showImage).resize(400,400).into(Binding.imageviewdetail)
        Binding.title.text = showtitle



    }

}