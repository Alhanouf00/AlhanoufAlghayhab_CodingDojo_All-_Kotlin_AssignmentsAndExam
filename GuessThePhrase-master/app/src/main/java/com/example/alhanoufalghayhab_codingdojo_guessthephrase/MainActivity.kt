package com.example.alhanoufalghayhab_codingdojo_guessthephrase

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    val characterList = listOf<String>("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
        "U","V","X","Y","Z"," ")
    var holdString = " "
    var oldValue= ""
    var counter = 4

    val phraseinput = ArrayList<String>()
    lateinit var custom:GeussPhrase
    lateinit var input :TextInputEditText
    lateinit var phrase: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        custom = GeussPhrase(phraseinput)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = custom
        generatePhrase()
        startGame()
        //butt.setOnClickListener {  }
    }
    fun generatePhrase()
    { phrase = findViewById<TextView>(R.id.displayphrase)
      input = findViewById<TextInputEditText>(R.id.inputPhrase)


        var randomNumber1 = (0..12).random()
        var randomNumber2 = (13..25).random()

       // var randomNumber1 = (0..2).random()
        //var randomNumber2 = (3..4).random()
        for (i in (randomNumber1..randomNumber2))
        {
            holdString += characterList.random()
           // phrase.text = characterList.random()

        }

        oldValue = holdString

        holdString = holdString.replace(Regex("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]"), "*")
        println("Old$oldValue")

        phrase.text = holdString

    }

    fun startGame()
    {
        var butt = findViewById<Button>(R.id.button)
        var holdInput = ""

        butt.setOnClickListener {
            checkPhrase(input.text.toString().uppercase())
            input.text = null

            if (counter==0)
            {
                butt.display
                CustomAlertDialog(" You lose the game the phras is $oldValue \n press yes if you want play agin")

            }
        }


    }
    fun checkPhrase( phraseGeusses: String)
    {
        var holdIndex = 0

        if (!oldValue.contains(phraseGeusses))
        {
            counter--
            println("IF ${oldValue.indexOf(phraseGeusses)} ")
            phraseinput.add("Wrong geuss : ${phraseGeusses}  now you have $counter try of geuss")
            custom.notifyDataSetChanged()



        }

        else if(oldValue.contains(phraseGeusses))
        {
            holdIndex = oldValue.indexOf(phraseGeusses)

            println("INDEX OF ${holdIndex}")
            println("oldvalue   ${oldValue[holdIndex]}       ${oldValue.length}      ")
            holdString = holdString.replaceRange(holdIndex..holdIndex,phraseGeusses)
            println("Hold $holdString")
            //ABC
            //***
            //A**
            //ABC**
            //***

            phrase.text = holdString.toString()
            phraseinput.add("Correct geuss : ${phraseGeusses} ")
            custom.notifyDataSetChanged()

        }
    }
    fun CustomAlertDialog( message: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }









}