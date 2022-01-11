package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Database.NoteDataBase
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.View.NotesAppSqlit
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.R
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.RecViewModel.RecNotesViewsModel
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.ActivityNotesAppViewModelBinding

class NotesAppViewModel : AppCompatActivity() {
    lateinit var Bindings: ActivityNotesAppViewModelBinding
    val noteModelView by lazy { ViewModelProvider(this@NotesAppViewModel).get(NotesAppSqlit::class.java) }
    val databaseNoteHelper by lazy { NoteDataBase(applicationContext) }

    lateinit var recViewNotes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindings = ActivityNotesAppViewModelBinding.inflate(layoutInflater)
        setContentView(Bindings.root)
        recViewNotes = findViewById(R.id.recviewnote)
        recViewNotes.layoutManager = LinearLayoutManager(this)
        buttonAdd()
        retrieveNotes()
        updateNotes()
    }

    fun buttonAdd()
    {
        Bindings.add.setOnClickListener {
            var noteSend = Bindings.notetextfield.text
            noteModelView.addNote(noteSend.toString())

            noteModelView.getNotes().observe(this@NotesAppViewModel,{
                noteCURD-> databaseNoteHelper.saveData(noteCURD)
                retrieveNotes()

            })
        }

    }

    fun retrieveNotes()
    {
            recViewNotes.adapter = RecNotesViewsModel(databaseNoteHelper.readData(),this@NotesAppViewModel)
            recViewNotes.adapter!!.notifyDataSetChanged()
    }

    fun updateNotes()
    {
        val getNote = intent.getStringExtra("note")
        val getPK = intent.getStringExtra("pk")
        if (getNote != null) {
            if (getPK != null) {
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

            noteModelView.addNote(notText.toString())
            noteModelView.addPK(pk)

            noteModelView.getNotes().observe(this@NotesAppViewModel,{

                noteCURD-> sendNote.notes = noteCURD

                noteModelView.getPK().observe(this@NotesAppViewModel,{
                        notePK-> sendNote.pk = notePK

                    databaseNoteHelper.updateNote(sendNote)
                    retrieveNotes()

                })

            })

        })

        builder.setNegativeButton("Delete",DialogInterface.OnClickListener { dialog, which ->
            noteModelView.addPK(pk)

            noteModelView.getPK().observe(this@NotesAppViewModel,{

                    notePK-> sendNote.pk = notePK

                databaseNoteHelper.deleteNote(sendNote)
                retrieveNotes()
            })


        })
        builder.create()
        builder.show()
    }
}