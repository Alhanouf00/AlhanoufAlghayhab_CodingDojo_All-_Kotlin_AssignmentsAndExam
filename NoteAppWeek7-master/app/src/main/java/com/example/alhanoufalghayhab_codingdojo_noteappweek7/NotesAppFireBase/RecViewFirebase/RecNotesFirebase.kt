package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFireBase.RecViewFirebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFireBase.NotesAppFirebase
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.RecViewNoteFirebaseBinding
import java.util.ArrayList

class RecNotesFirebase(val listOfNotes: ArrayList<NotesData>, val activity: Context): RecyclerView.Adapter<RecNotesFirebase.RecNotesFirebaseView>() {
    inner class RecNotesFirebaseView(val Binding: RecViewNoteFirebaseBinding) : RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesFirebaseView {
        return RecNotesFirebaseView(
            RecViewNoteFirebaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecNotesFirebaseView, position: Int) {
        var holdListOfNote  = listOfNotes



        holder.Binding.apply {


            notebodyfirebase.text = listOfNotes[position].notes


            notebodyfirebase .setOnClickListener {
                    val intent = Intent(
                        activity,
                        NotesAppFirebase::class.java
                    ) //context we got from constructor
                    intent.putExtra("note", listOfNotes[position].notes)
                    intent.putExtra("pk", "${listOfNotes[position].pk}")
                    activity.startActivity(intent)
                }
        }


    }


    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}

