package com.example.alhanoufalghayhab_codingdojo_helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    fun hello()
    {
       println("Hi I am function hello")

    }
     var x = 7
    var y = 2



    override fun onCreate(savedInstanceState: Bundle?) {

        if (x+y == 9)
        {
            print("Hello Kotlin!! ")

        }
        hello()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}