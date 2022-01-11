package com.example.alhanoufalghayhab_codingdojo_callbacksapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_callbacksapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        Toast.makeText(this@MainActivity,"The state of main acticity is ${lifecycle.currentState}", Toast.LENGTH_SHORT).show()
        Log.d("MainActivity","The state of main acticity is ${lifecycle.currentState}")
        go()
    }


    fun go()
    {
        Binding.button.setOnClickListener{

            val intent = Intent(this@MainActivity, AnotherActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@MainActivity,"The state of main acticity is ${lifecycle.currentState}", Toast.LENGTH_SHORT).show()
            Log.d("MainActivity","The state of main acticity is ${lifecycle.currentState}")


        }


    }
}