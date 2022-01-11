package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.RecViewModel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.NotesAppViewModel
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.RecViewNoteViewModelBinding

class RecNotesViewsModel(val listOfNotes:ArrayList<NotesData>, val activity: Context): RecyclerView.Adapter<RecNotesViewsModel.RecNotesView>() {
    inner class RecNotesView(val Binding: RecViewNoteViewModelBinding) : RecyclerView.ViewHolder(Binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesView {
        return RecNotesView(RecViewNoteViewModelBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun onBindViewHolder(holder: RecNotesView, position: Int) {
        holder.Binding.apply {
            notebody.text = listOfNotes[position].notes
            notebody.setOnClickListener {
                val intent= Intent(activity, NotesAppViewModel::class.java) //context we got from constructor
                intent.putExtra("note",listOfNotes[position].notes)
                intent.putExtra("pk","${listOfNotes[position].pk}")
                activity.startActivity(intent)
            }
        }
    }
    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}