package com.example.alhanoufalghayhab_codingdojo_headsupgame.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    lateinit var Binding: ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        gotoHeadsUpPrep()
        gotoHeadsUpIntro()
        gotoHeadsUpPrepLocal()

    }

    fun gotoHeadsUpPrep()
    {
        Binding.prep.setOnClickListener {
             val goToHeadsUpPrep = Intent(this@MainScreen,HeadsUpPrep::class.java)
            startActivity(goToHeadsUpPrep)
        }
    }

    fun gotoHeadsUpIntro()
    {
        Binding.Intro.setOnClickListener {
            val goToHeadsUpIntro = Intent(this@MainScreen,HeadsUpIntro::class.java)
            startActivity(goToHeadsUpIntro)
        }
    }

    fun gotoHeadsUpPrepLocal()
    {
        Binding.preplocal.setOnClickListener {
            val goToHeadsUpPrep = Intent(this@MainScreen,HeadsUpPrepLocally::class.java)
            startActivity(goToHeadsUpPrep)
        }
    }
}