package com.example.alhanoufalghayhab_codingdojo_menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu , menu)

        return true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.home -> {
                Toast.makeText(this@Home, "You are in Home Screen ", Toast.LENGTH_LONG).show()

            }
            R.id.about -> {
                val intenabout = Intent(this@Home,About::class.java)
                startActivity(intenabout)

            }
            R.id.help -> {
                val intenhelp = Intent(this@Home,Help::class.java)
                startActivity(intenhelp)

            }

        }
        return super.onOptionsItemSelected(item)
    }
}