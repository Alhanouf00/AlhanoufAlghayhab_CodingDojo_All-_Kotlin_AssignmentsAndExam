package com.example.alhanoufalghayhab_codingdojo_notificationsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_notificationsapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    lateinit var Bindings: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindings = ActivityMealBinding.inflate(layoutInflater)
        setContentView(Bindings.root)
        showText()
    }




    fun showText()
    {
        var textMessage = intent.getStringExtra("meal")

        Bindings.textView.text = textMessage

    }
}