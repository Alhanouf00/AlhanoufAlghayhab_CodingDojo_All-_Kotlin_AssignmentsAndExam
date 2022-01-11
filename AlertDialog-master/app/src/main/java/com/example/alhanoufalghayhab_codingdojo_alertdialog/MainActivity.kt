package com.example.alhanoufalghayhab_codingdojo_alertdialog

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alhanoufalghayhab_codingdojo_alertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        button()
    }

    fun button()
    {
        val alert = AlertDialog.Builder(this)
        var inputUser = EditText(this)
        var displayText = TextView(this)
        var layoutalert= LinearLayout(this)
        Binding.button.setOnClickListener {
            inputUser.hint = "Enter a Text "
            displayText.text = "Here"
            layoutalert.addView(inputUser)
            layoutalert.addView(displayText)
            layoutalert.setOrientation(LinearLayout.HORIZONTAL)
            layoutalert.orientation

            alert.setNegativeButton("Display In Text",DialogInterface.OnClickListener { dialog, which ->
                displayText.text = inputUser.text.toString()
                Toast.makeText(this@MainActivity,"Your twxt is ${displayText.text}",Toast.LENGTH_SHORT).show()

            })

            alert.setNeutralButton("Go To Another Activity",DialogInterface.OnClickListener { dialog, which ->
                val anotherAc = Intent(this,AnotherActivity::class.java)
                val holdInput = anotherAc.putExtra("input",inputUser.text.toString())
                startActivity(anotherAc)
            })
            alert.setView(layoutalert)
            alert.create()
            alert.show()
        }

    }
}