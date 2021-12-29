package com.example.alhanoufalghayhab_codingdojo_2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pressButton()

    }

    fun pressButton()
    {
        var geussNumber = findViewById<Button>(R.id.numbergame)
        var geussPhrase = findViewById<Button>(R.id.geussgame)



        geussNumber.setOnClickListener {
            val intent = Intent(this,NumberGamesActivity::class.java)
            startActivity(intent)

        }
        geussPhrase.setOnClickListener {
            val intentp = Intent(this,GeussGamePhrase::class.java)
            startActivity(intentp)

        }
    }

}