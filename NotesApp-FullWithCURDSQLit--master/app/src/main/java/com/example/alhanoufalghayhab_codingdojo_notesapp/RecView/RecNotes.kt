package com.example.alhanoufalghayhab_codingdojo_notesapp.RecView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesapp.MainActivity
import com.example.alhanoufalghayhab_codingdojo_notesapp.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_notesapp.databinding.RecNotesViewBinding

class RecNotes(val listOfNotes:ArrayList<NotesData>,val activity: Context):RecyclerView.Adapter<RecNotes.RecNotesView>() {
    inner class RecNotesView(val Binding: RecNotesViewBinding) : RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesView {
        return RecNotesView(RecNotesViewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }


    override fun onBindViewHolder(holder: RecNotesView, position: Int) {
        holder.Binding.apply {
            notebody.text = listOfNotes[position].notes


            notebody.setOnClickListener {
                val intent= Intent(activity, MainActivity::class.java) //context we got from constructor
                intent.putExtra("note",listOfNotes[position].notes)
                intent.putExtra("pk","${listOfNotes[position].pk}")
                activity.startActivity(intent)
                Log.d("1Text","${listOfNotes[position].pk}")
            }

        }
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}