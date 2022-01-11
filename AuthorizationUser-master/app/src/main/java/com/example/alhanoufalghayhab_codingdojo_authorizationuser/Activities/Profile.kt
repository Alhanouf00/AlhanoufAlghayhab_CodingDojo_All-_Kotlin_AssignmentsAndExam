package com.example.alhanoufalghayhab_codingdojo_authorizationuser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    lateinit var Binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        showProfile()
    }


    fun showProfile()
    {
        Binding.nameview.text = intent.getStringExtra("name")
        Binding.emailview.text = intent.getStringExtra("email")
    }

}