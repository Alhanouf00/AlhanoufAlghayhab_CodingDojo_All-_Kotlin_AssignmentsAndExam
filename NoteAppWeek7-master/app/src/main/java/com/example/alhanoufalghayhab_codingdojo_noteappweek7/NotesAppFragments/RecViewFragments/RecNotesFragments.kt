package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.RecViewFragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.NotesAppFragment
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.databinding.RecViewNoteFragmentBinding

class RecNotesFragments(val listOfNotes: ArrayList<NotesData>, val activity: Context):RecyclerView.Adapter<RecNotesFragments.RecNotesFragmentsView>() {
    inner class RecNotesFragmentsView(val Binding: RecViewNoteFragmentBinding): RecyclerView.ViewHolder(Binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesFragmentsView {
        return RecNotesFragmentsView(
            RecViewNoteFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: RecNotesFragmentsView, position: Int) {

        holder.Binding.apply {
            notebodyfragment.text = listOfNotes[position].notes
            notebodyfragment .setOnClickListener {
                val intent = Intent(
                    activity,
                    NotesAppFragment::class.java
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