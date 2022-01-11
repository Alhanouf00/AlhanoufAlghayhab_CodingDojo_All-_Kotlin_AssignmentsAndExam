package com.example.alhanoufalghayhab_codingdojo_callbacksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_callbacksapp.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {
    lateinit var Binding: ActivityAnotherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        Toast.makeText(this@AnotherActivity,"The state of another acticity is ${lifecycle.currentState}", Toast.LENGTH_SHORT).show()
        Log.d("AnotherActivity","The state of main acticity is ${lifecycle.currentState}")
    }
}