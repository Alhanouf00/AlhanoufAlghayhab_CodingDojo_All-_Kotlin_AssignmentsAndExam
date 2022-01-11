package com.example.alhanoufalghayhab_codingdojo_appwithsinglebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_appwithsinglebutton.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        displayInTextView()
    }



    fun displayInTextView()
    {
        Binding.button.setOnClickListener {

         if (!Binding.userinput.text.isNullOrEmpty()) {
             Binding.textView.text = Binding.userinput.text
         }

            else
         {
             Toast.makeText(this@MainActivity,"Please enter your text first",Toast.LENGTH_LONG).show()

         }






        }
    }
}