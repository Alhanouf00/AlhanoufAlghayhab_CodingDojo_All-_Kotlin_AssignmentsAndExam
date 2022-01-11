package com.example.alhanoufalghayhab_codingdojo_menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu , menu)

        return true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {

            R.id.help -> {

                Toast.makeText(this@Help, "You are in Help Screen ", Toast.LENGTH_LONG).show()

            }
            R.id.home-> {
                val intenhome = Intent(this@Help,Home::class.java)
                startActivity(intenhome)
            }

            R.id.about -> {
                val intenhelp = Intent(this@Help,About::class.java)
                startActivity(intenhelp)

            }




        }
        return super.onOptionsItemSelected(item)
    }

}