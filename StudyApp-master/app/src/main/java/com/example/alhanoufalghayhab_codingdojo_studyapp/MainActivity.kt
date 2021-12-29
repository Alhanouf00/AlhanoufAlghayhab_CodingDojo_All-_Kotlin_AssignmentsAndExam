package com.example.alhanoufalghayhab_codingdojo_studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
val listArray = ArrayList<String>()
    private lateinit var recStudy: RecyclerView

    val listOfSummary = mutableMapOf<String,String>("Const and variable" to "var a = 6 -> can changing " + "Val b = 8 -> cannot changing"
             , "Data Types" to "String – Int - Float " + "Double - Char – Boolean "
            ,"Dictionary" to "val example = mapOf (key to value) - > is immutable " +
            "print(example[key]) - > to print specific value of the key ","Functions" to "fun example () -> function without return and argument " +
            "{ " +
            "Do your program " +
            "} " +
            "fun example (example: data type) function with argument but without return " +
            "{ " +
            "Do your program " +
            "} " +
            "fun example (): data type function with return but without argument  " +
            "{ " +
            "Do your program " +
            "} " +
            "fun example (example: data type): data type function with return and argument " +
            "{ " +
            "Do your program " +
            "} ","Lists and Array " to "val example = listOf(“example”) -> fixed size and immutable " +
            "val example = arrayOf(“example”) -> fixed size and mutable ")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //println("Ssssssssssss$listOfSummary")
        recStudy = findViewById(R.id.recView)
        recStudy.layoutManager = LinearLayoutManager(this)

        listArray.add(listOfSummary.toString())
        recStudy.adapter = Study(listOfSummary)
       // addToArray()

    }

/*
    fun addToArray()
    {
        for (key in listOfSummary.keys)
        {
            listArray.add(key.toString()+listOfSummary[key].toString())

        }
        println("LIstOfArray  $listArray")


        //println("Here list ${listArray[0]}" )
        recStudy.adapter!!.notifyDataSetChanged()


    }

 */




}