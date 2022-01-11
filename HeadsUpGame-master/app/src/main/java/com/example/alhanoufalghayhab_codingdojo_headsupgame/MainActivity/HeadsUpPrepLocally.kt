package com.example.alhanoufalghayhab_codingdojo_headsupgame.MainActivity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_headsupgame.DataBase.HeadsUpDataHelper
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.CelebrityListData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.HeadsUpLocalData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.R
import com.example.alhanoufalghayhab_codingdojo_headsupgame.RecView.RecHeadsUpPreperLocally
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.ActivityHeadsUpPrepLocallyBinding
import com.google.android.material.textfield.TextInputEditText

class HeadsUpPrepLocally : AppCompatActivity() {
    lateinit var Binding: ActivityHeadsUpPrepLocallyBinding
    lateinit var recViewCelebrity: RecyclerView
    val databaseNoteHelper by lazy { HeadsUpDataHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityHeadsUpPrepLocallyBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewCelebrity = findViewById(R.id.headsuppreplocally)
        recViewCelebrity.layoutManager = LinearLayoutManager(this)
        addButton()
        retrieveCelebrity()
    }


    fun addButton() {
        Binding.addHeadsLocally.setOnClickListener {

            addNewCelebrity()
        }


    }


    fun addNewCelebrity() {
        val buildDilog: AlertDialog.Builder = AlertDialog.Builder(this@HeadsUpPrepLocally)
        val viewShow = layoutInflater.inflate(R.layout.add_to_headsup_prep, null)
        var nameText = viewShow.findViewById<TextInputEditText>(R.id.name_text).text
        var taboo1Text = viewShow.findViewById<TextInputEditText>(R.id.taboo1_text).text
        var taboo2Text = viewShow.findViewById<TextInputEditText>(R.id.taboo2_text).text
        var taboo3Text = viewShow.findViewById<TextInputEditText>(R.id.taboo3_text).text


        buildDilog.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which ->


            databaseNoteHelper.saveData(nameText.toString(),
                taboo1Text.toString(),
                taboo2Text.toString(),
                taboo3Text.toString()
            )
            retrieveCelebrity()

        })
        buildDilog.setView(viewShow)
        buildDilog.create()
        buildDilog.show()


    }



    fun retrieveCelebrity()
    {
        recViewCelebrity.adapter = RecHeadsUpPreperLocally(databaseNoteHelper.readData())
        recViewCelebrity.adapter!!.notifyDataSetChanged()



    }


}