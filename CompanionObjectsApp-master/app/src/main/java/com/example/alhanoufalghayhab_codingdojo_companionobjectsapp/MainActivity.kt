package com.example.alhanoufalghayhab_codingdojo_companionobjectsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_companionobjectsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        change()
    }
    
    fun change()
    {
        Binding.change.setOnClickListener {
            DayOrNight.changeBackground(Binding.back,Binding.input.text.toString(),Binding.change,this)
        }

    }
}