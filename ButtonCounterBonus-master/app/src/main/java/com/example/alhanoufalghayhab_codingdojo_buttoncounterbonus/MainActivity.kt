package com.example.alhanoufalghayhab_codingdojo_buttoncounterbonus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_buttoncounterbonus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        counter()
    }


    fun counter()
    {
        var count = 26

        Binding.button.setOnClickListener {
            count++
            Binding.textView.text = count.toString()


        }

    }
}