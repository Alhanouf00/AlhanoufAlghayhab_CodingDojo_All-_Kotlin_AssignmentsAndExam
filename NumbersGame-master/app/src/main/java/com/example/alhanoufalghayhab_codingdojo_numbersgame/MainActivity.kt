package com.example.alhanoufalghayhab_codingdojo_numbersgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var custom: NumberActivity
    val numberGeuss = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        custom = NumberActivity(numberGeuss)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = custom
        pressHere()
    }


    fun pressHere()  {
        var count = 0
        var submit = findViewById<Button>(R.id.geuss)
        var input = findViewById<TextInputEditText>(R.id.numberinput)
        submit.setOnClickListener {



            gameGeuss(input.text.toString())
            input.text = null

        }
    }


    fun gameGeuss(holdNumber:String) {

        var randomNumber = (0..10).random()
            if (holdNumber.toInt() != randomNumber) {
                numberGeuss.add("The geuss is incorrect the correct number is ($randomNumber)")
                println(numberGeuss)
                custom.notifyDataSetChanged()

            }

            else if (holdNumber.toInt() == randomNumber) {
                numberGeuss.add("You are greate your geuss is correct")
                custom.notifyDataSetChanged()

            }

    }
}