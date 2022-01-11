package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFireBase

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.R
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFireBase.RecViewFirebase.RecNotesFirebase
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.ActivityNotesAppFirebaseBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class NotesAppFirebase : AppCompatActivity() {
    lateinit var Bindings: ActivityNotesAppFirebaseBinding
    val db = Firebase.firestore
    lateinit var recViewNotes: RecyclerView
    val noteData = ArrayList<NotesData>()
    var pk = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindings = ActivityNotesAppFirebaseBinding.inflate(layoutInflater)
        setContentView(Bindings.root)
        buttonAdd()
        recViewNotes = findViewById(R.id.recviewnotefirebase)
        recViewNotes.layoutManager = LinearLayoutManager(this)
        retrieveNotes()
        updateNotes()
    }
    fun buttonAdd()
    {
        Bindings.addtofirebase.setOnClickListener {
            var noteSend = Bindings.notetextfieldfirebase.text

                val noteUser = hashMapOf(
                     "$pk" to "${noteSend.toString()}",
                )

            db.collection("NotesUsers").document("$pk")
                .set(noteUser)
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error writing document", e)
                }
            retrieveNotes()
            pk++
        }
    }
    fun retrieveNotes()
    {
        db.collection("NotesUsers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                     noteData.add(NotesData(document.id.toInt(), document.data.values.toString()))
                     Log.d("", "${document.data}")
                    recViewNotes.adapter = RecNotesFirebase(noteData,this@NotesAppFirebase)
                    recViewNotes.adapter!!.notifyDataSetChanged()
                }

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
        noteData.clear()

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
            db.collection("NotesUsers").document("$pk").update("$pk",notText.toString())
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }

                    retrieveNotes()
        })
        builder.setNegativeButton("Delete", DialogInterface.OnClickListener { dialog, which ->

            db.collection("NotesUsers").document("$pk").delete()
                retrieveNotes()
        })
        builder.create()
        builder.show()
    }
}