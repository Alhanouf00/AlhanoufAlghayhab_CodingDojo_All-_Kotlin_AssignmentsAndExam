package com.example.alhanoufalghayhab_codingdojo_simplebuttonlistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_simplebuttonlistener.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        buttonKotlinCode()
    }


    fun buttonKotlinCode()
    {
        Binding.kotlincode.setOnClickListener {

            Toast.makeText(this,"Hi i am button with kotlin code",Toast.LENGTH_SHORT).show()
        }


    }

    fun buttonXMLCode(view:View)
    {
        Toast.makeText(this@MainActivity,"Hi i am button with XML code",Toast.LENGTH_SHORT).show()
    }

}