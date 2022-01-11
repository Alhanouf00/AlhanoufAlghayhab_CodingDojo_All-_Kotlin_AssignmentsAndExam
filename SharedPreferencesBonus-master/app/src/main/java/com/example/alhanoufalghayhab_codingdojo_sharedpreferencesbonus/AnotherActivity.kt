package com.example.alhanoufalghayhab_codingdojo_sharedpreferencesbonus

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alhanoufalghayhab_codingdojo_sharedpreferencesbonus.databinding.ActivityAnotherBinding


class AnotherActivity : AppCompatActivity() {
    lateinit var Binding: ActivityAnotherBinding
    lateinit var reciveData : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        reciveFromAnything()
        reciveData = getSharedPreferences("input1" , MODE_PRIVATE)
        reciveData = getSharedPreferences("input2" , MODE_PRIVATE)
    }


    fun reciveFromAnything()
    {
        Binding.textView.text = intent.getStringExtra("input1")+"\n"+ intent.getStringExtra("input2")

        Binding.button.setOnClickListener {

            Binding.textView.text = reciveData.getString("input1","")+"\n"+ reciveData.getString("input2","")
            Binding.textView.setTextColor(Color.parseColor("#8226C6DA"))

        }

    }
}