package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.Fragments.FragmentNotesEdit
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.R
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.RecViewFragments.RecNotesFragments
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.ActivityNotesAppFragmentBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class NotesAppFragment : AppCompatActivity() {
    lateinit var Bindings: ActivityNotesAppFragmentBinding
    lateinit var recViewNotesFragment: RecyclerView
    private lateinit var navController: NavController

    val db = Firebase.firestore


    val noteData = ArrayList<NotesData>()
    var pk = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindings = ActivityNotesAppFragmentBinding.inflate(layoutInflater)
        setContentView(Bindings.root)
        recViewNotesFragment = findViewById(R.id.recviewnotefragment)
        recViewNotesFragment.layoutManager = LinearLayoutManager(this)
        buttonAdd()
        updateNotes()
        retrieveNotes()
    }
    fun buttonAdd()
    {
        //val uuid = UUID.randomUUID().toString() is not work cause i should know how to reach it
        Bindings.addfragment.setOnClickListener {
            var fragem= FragmentNotesEdit()

            navController = Navigation.findNavController(this, R.id.fragement)

            /*
            var noteSend = Bindings.notetextfieldfragment.text

            val noteUser = hashMapOf(
                "$pk" to "${noteSend.toString()}",
            )

            db.collection("FragmentNoteUser").document("$pk")
                .set(noteUser)
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error writing document", e)
                }
            retrieveNotes()
            pk++

             */
        }
    }
    fun retrieveNotes()
    {
        db.collection("FragmentNoteUser")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    noteData.add(NotesData(document.id.toInt(), document.data.values.toString()))
                    Log.d("Note123", "${document.id}")
                    recViewNotesFragment.adapter = RecNotesFragments(noteData,this@NotesAppFragment)
                    recViewNotesFragment.adapter!!.notifyDataSetChanged()

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
                //showDialog(getNote,getPK.toInt())


            }
        }

    }

}