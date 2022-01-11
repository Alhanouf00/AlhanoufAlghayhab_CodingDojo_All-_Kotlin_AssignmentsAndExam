package com.example.alhanoufalghayhab_codingdojo_notesapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesapp.DataBase.NoteDataBase
import com.example.alhanoufalghayhab_codingdojo_notesapp.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_notesapp.RecView.RecNotes
import com.example.alhanoufalghayhab_codingdojo_notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     val databaseNoteHelper by lazy { NoteDataBase(applicationContext) }
    lateinit var Binding: ActivityMainBinding
   lateinit var recViewNotes: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewNotes = findViewById(R.id.recviewnote)
        recViewNotes.layoutManager = LinearLayoutManager(this)
        addNote()
        retrieveNotes()
        updateNotes()

    }


    fun addNote()
    {
        Binding.add.setOnClickListener {
            if(!Binding.notetextfield.text.isNullOrEmpty())
            {
                val note = Binding.notetextfield.text.toString()
                databaseNoteHelper.saveData(note)

                Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
                retrieveNotes()

            }
        }
    }


    fun retrieveNotes()
    {
        recViewNotes.adapter = RecNotes(databaseNoteHelper.readData(),this@MainActivity)
        recViewNotes.adapter!!.notifyDataSetChanged()
    }


    fun updateNotes()
    {
        val getNote = intent.getStringExtra("note")
        val getPK = intent.getStringExtra("pk")
        Log.d("123","PK : $getPK")
        if (getNote != null) {
            if (getPK != null) {
                Log.d("123","PK : $getPK")
                showDialog(getNote,getPK.toInt())
            }
        }
    }



    fun showDialog(note:String,pk:Int)
    {
        var sendNote = NotesData(0,"")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Update")
        builder.setMessage("You want edit or delete the note")
        val noteUser = EditText(this)
        noteUser.hint = note
        builder.setView(noteUser)

        builder.setPositiveButton("Edit", DialogInterface.OnClickListener { dialog, which ->
            var notText = noteUser.text

            Log.d("1Text","${notText.toString()}")

            sendNote =  NotesData(pk,notText.toString())

            databaseNoteHelper.updateNote(sendNote)
            retrieveNotes()
        })


        builder.setNegativeButton("Delete",DialogInterface.OnClickListener { dialog, which ->
            sendNote = NotesData(pk,"")
            databaseNoteHelper.deleteNote(sendNote)
            retrieveNotes()



        })


        builder.create()
        builder.show()


    }


}