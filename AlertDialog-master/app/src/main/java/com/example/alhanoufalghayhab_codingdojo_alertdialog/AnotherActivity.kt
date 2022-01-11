package com.example.alhanoufalghayhab_codingdojo_alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_alertdialog.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {
    lateinit var Binding:ActivityAnotherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        display()
    }


    fun display()
    {
        Binding.textView.text = intent.getStringExtra("input")

    }

}