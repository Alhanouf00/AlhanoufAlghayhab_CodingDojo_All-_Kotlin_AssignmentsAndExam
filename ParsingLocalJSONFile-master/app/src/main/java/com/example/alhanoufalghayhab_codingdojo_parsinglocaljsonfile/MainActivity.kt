package com.example.alhanoufalghayhab_codingdojo_parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.core.text.htmlEncode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_parsinglocaljsonfile.databinding.ActivityMainBinding
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var RecViewImageFeed: RecyclerView
    val listImage = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        RecViewImageFeed = findViewById(R.id.recviewimage)
        RecViewImageFeed.layoutManager = LinearLayoutManager(this)
        featchImage()
    }


    fun featchImage() {

        val openImageFile = assets.open("data.json")
        val jsonData = openImageFile.bufferedReader().use { it.readText() }
        var jsonImage = JSONArray(jsonData)
        for (i in 0..jsonImage.length() - 1) {
            var holdURL = jsonImage.getJSONObject(i).getString("hdurl")
            listImage.add(holdURL)

        }

        RecViewImageFeed.adapter = RecImage(listImage)
        RecViewImageFeed.adapter!!.notifyDataSetChanged()
    }
}