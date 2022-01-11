package com.example.alhanoufalghayhab_codingdojo_menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu , menu)

        return true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {

            R.id.about -> {

                Toast.makeText(this@About, "You are in About Screen ", Toast.LENGTH_LONG).show()
            }


            R.id.home-> {
                    val intenhome = Intent(this@About,Home::class.java)
                    startActivity(intenhome)
                }



            R.id.help -> {
                val intenhelp = Intent(this@About,Help::class.java)
                startActivity(intenhelp)

            }

        }
        return super.onOptionsItemSelected(item)
    }




}