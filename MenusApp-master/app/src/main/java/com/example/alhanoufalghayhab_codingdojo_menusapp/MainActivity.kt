package com.example.alhanoufalghayhab_codingdojo_menusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mainMenu: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainMenu = findViewById(R.id.mainMenu)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.mainmenus,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            //
            R.id.store -> {
                 Snackbar.make(mainMenu,"You press store",Snackbar.LENGTH_SHORT).show()

            return true}
            R.id.order -> {
                Snackbar.make(mainMenu,"You press order",Snackbar.LENGTH_SHORT).show()

                return true}
            R.id.cart -> {
                Snackbar.make(mainMenu,"You press cart",Snackbar.LENGTH_SHORT).show()

                return true}

        }
        return super.onOptionsItemSelected(item)
    }
}