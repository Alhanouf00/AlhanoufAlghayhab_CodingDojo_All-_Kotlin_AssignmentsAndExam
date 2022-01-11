package com.example.alhanoufalghayhab_codingdojo_3buttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_3buttons.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {
    lateinit var Bindingg: ActivityAnotherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindingg = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(Bindingg.root)
        reciveEditeText()
    }


    fun reciveEditeText()
    {
        val reciveInput1 = intent.getStringExtra("Edite1")
        val reciveInput2 = intent.getStringExtra("Edite2")
        val reciveInput3 = intent.getStringExtra("Edite3")

        Bindingg.textView2.text = "Edit Text1: $reciveInput1\n"+"Edit Text2: $reciveInput2\n"+"Edit Text3: $reciveInput3"


    }

}