package com.example.alhanoufalghayhab_codingdojo_notesappfragment.RecView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.databinding.RecViewNotesBinding

class RecNotesApp(val listOfNotes: ArrayList<NotesData>, val activity: Context): RecyclerView.Adapter<RecNotesApp.RecNotesAppView>() {



    inner class RecNotesAppView(val Binding: RecViewNotesBinding): RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesAppView {

        return RecNotesAppView(RecViewNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecNotesAppView, position: Int) {

        holder.Binding.apply {
            notebodyfragment.text = listOfNotes[position].notes
            notebodyfragment .setOnClickListener {
                Toast.makeText(activity,"The pk is ${listOfNotes[position].pk}",Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}
