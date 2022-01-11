package com.example.alhanoufalghayhab_codingdojo_notesapproom

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesapproom.RecView.RecNotes
import com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms.EntityNote
import com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms.NotesDatabase
import com.example.alhanoufalghayhab_codingdojo_notesapproom.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var recViewNotes: RecyclerView
    //val listOfNote = List<EntityNote>
    private val noteDao by lazy { NotesDatabase.getDatabase(this).noteDao() }


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

            val noteUser = Binding.notetextfield.text

            CoroutineScope(IO).launch {

                noteDao.addNote(EntityNote(0, noteUser.toString()))
                retrieveNotes()

            }

            Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
            retrieveNotes()
        }
            }

    fun retrieveNotes()
    {
        CoroutineScope(IO).launch {
            val noteData = async { noteDao.getAllNotes() }.await()
            if (noteData !=null) {

                withContext(Main) {
                    recViewNotes.adapter = RecNotes(noteData, this@MainActivity)
                    recViewNotes.adapter!!.notifyDataSetChanged()
                }

            }
        }
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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Update")
        builder.setMessage("You want edit or delete the note")
        val noteUser = EditText(this)
        noteUser.hint = note
        builder.setView(noteUser)
        builder.setPositiveButton("Edit", DialogInterface.OnClickListener { dialog, which ->
            var notText = noteUser.text

            CoroutineScope(IO).launch {
                noteDao.updateNote(EntityNote(pk, notText.toString()))
            }
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_LONG).show()

            retrieveNotes()
        })
        builder.setNegativeButton("Delete",DialogInterface.OnClickListener { dialog, which ->
            CoroutineScope(IO).launch {
                noteDao.deleteNote(EntityNote(pk,""))
            }
            retrieveNotes()

        })
        builder.create()
        builder.show()
    }


}