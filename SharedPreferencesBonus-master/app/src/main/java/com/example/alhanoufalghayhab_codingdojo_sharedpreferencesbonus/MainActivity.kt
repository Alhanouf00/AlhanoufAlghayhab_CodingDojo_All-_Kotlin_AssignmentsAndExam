package com.example.alhanoufalghayhab_codingdojo_sharedpreferencesbonus

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.alhanoufalghayhab_codingdojo_sharedpreferencesbonus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    lateinit var saveData : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        saveData = getSharedPreferences("input1" , MODE_PRIVATE)
        saveData = getSharedPreferences("input2" , MODE_PRIVATE)
        Anything()
    }


    fun Anything()
    {
        var saveInput1 = saveData.edit()
        var saveInput2 = saveData.edit()



        Binding.go.setOnClickListener {
            val goToAnothe = Intent(this,AnotherActivity::class.java)
            goToAnothe.putExtra("input1",Binding.input1.text.toString())
            goToAnothe.putExtra("input2",Binding.input2.text.toString())
            startActivity(goToAnothe)
        }


        Binding.save.setOnClickListener {


            saveInput1.putString("input1", Binding.input1.text.toString())
            saveInput2.putString("input2", Binding.input2.text.toString())
            saveInput1.apply()
            saveInput2.apply()



        }


    }


}