package com.example.alhanoufalghayhab_codingdojo_youtubeapp

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_youtubeapp.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    lateinit var recViewVideo: RecyclerView
    lateinit var playYouTube: YouTubePlayer
     val listVideo = mutableMapOf<String,String>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewVideo = findViewById(R.id.recvideo)
        recViewVideo.layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
        checkConnect()
        listOfVideo()
        playVideo()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun checkConnect()
    {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connManager.activeNetwork == null)
        {
            AlertDialog.Builder(this).setTitle("No Internet")
                .setMessage("Please Connect to a Network")
                .setPositiveButton("OK",DialogInterface.OnClickListener { dialog, which -> checkConnect() })
                .create()
                .show()
        }
    }

    fun listOfVideo()
    {
        listVideo.put("Kotlin Tutorial Companion Object","Xiqfy0OpZnc")
        listVideo.put("RecyclerView OnClickListener","69C1ljfDvl0")
        listVideo.put("Android Studio - Pop Up Window","4GYKOzgQDWI")
        listVideo.put("Lambda in Kotlin","c33PORUrCJg")
        listVideo.put("KOTLIN Retrofit Tutorial","5gFrXGbQsc8")


    }

    fun playVideo()
    {
        Binding.youtubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                playYouTube = youTubePlayer
                playYouTube.loadVideo("Y0Dj-VCXE_o", 0F)
                recViewVideo.adapter = RecVideo(listVideo,playYouTube)
                recViewVideo.adapter!!.notifyDataSetChanged()
            }


        })


    }



}