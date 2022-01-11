package com.example.alhanoufalghayhab_codingdojo_noteappweek7.MainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.NotesAppViewModel
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFireBase.NotesAppFirebase
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.NotesAppFragment
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    lateinit var Binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        go()

    }

    fun go()
    {
        Binding.viewmodel.setOnClickListener {
            val intent = Intent(this@MainScreen, NotesAppViewModel::class.java)
            startActivity(intent)

        }


        Binding.firebasebutton.setOnClickListener {
            val intent = Intent(this@MainScreen, NotesAppFirebase::class.java)
            startActivity(intent)
        }

        Binding.fragmentsbutton.setOnClickListener {
            val intent = Intent(this@MainScreen, NotesAppFragment::class.java)
            startActivity(intent)
        }

    }




}