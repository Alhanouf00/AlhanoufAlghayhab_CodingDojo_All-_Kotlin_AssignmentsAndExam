package com.example.alghyhab_codingdojo_buttonapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        button()
    }

fun button() {


    var add = findViewById<Button>(R.id.Add)
    var minus = findViewById<Button>(R.id.Minus)

    add.setOnClickListener {countNumber("+") }

    minus.setOnClickListener {countNumber("-")}
}
    var counter = 0
    fun countNumber(button:String )
    {
        var myCounter = findViewById<TextView>(R.id.counter)




        if (button =="+")
        {
            counter++
            print("Counter+ = $counter")
            myCounter.text = counter.toString()
            if(counter>0) {
                myCounter.setTextColor(Color.GREEN)
            }
            if(counter==0) {
                myCounter.setTextColor(Color.BLACK)
            }

        }

        else if (button =="-")
        {
            print("Counter- = $counter")
            counter--
            myCounter.text = counter.toString()
            if(counter< 0) {
                myCounter.setTextColor(Color.RED)
            }
            if(counter==0) {
                myCounter.setTextColor(Color.BLACK)
            }

        }
    }

}
