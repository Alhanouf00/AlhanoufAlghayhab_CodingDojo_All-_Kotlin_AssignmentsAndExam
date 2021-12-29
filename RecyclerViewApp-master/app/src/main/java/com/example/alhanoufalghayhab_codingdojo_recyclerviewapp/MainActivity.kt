package com.example.alhanoufalghayhab_codingdojo_recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    val itemsList = ArrayList<String>()
    lateinit var custom:recview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.listofitem)
        custom = recview(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = custom

         addItem()

    }


    fun addItem()
    {
        var submit= findViewById<Button>(R.id.button3)
        var input = findViewById<TextInputEditText>(R.id.input)

        submit.setOnClickListener{


            itemsList.add(input.text.toString())
            input.text = null

            custom.notifyDataSetChanged()
        }

    }

}