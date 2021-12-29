package com.example.alhanoufalghayhab_codingdojo_activitylifecycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("TAGG","Here onCreate ")
    }

    override fun onStart() {
        super.onStart()
        Log.i("TAGG","Here onStart ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAGG","Here onResume ")

    }

    override fun onPause() {
        super.onPause()
        Log.i("TAGG","Here onPause ")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAGG","Here onStop ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("TAGG","Here onRestart ")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAGG","Here onDestroy ")
    }
}