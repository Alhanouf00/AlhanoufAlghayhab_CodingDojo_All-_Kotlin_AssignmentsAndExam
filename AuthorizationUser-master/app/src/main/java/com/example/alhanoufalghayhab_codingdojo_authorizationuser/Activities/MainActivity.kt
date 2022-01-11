package com.example.alhanoufalghayhab_codingdojo_authorizationuser.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.R
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        goSingup()
        goLogin()
    }


    fun goSingup()
    {

        Binding.singup.setOnClickListener {

            val intent = Intent(this@MainActivity, Singup::class.java)
            startActivity(intent)
        }


    }
    fun goLogin()
    {

        Binding.login.setOnClickListener {

            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }


    }


}