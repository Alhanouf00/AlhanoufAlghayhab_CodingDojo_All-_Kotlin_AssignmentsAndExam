package com.example.alhanoufalghayhab_codingdojo_notesapproom.RecView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesapproom.MainActivity
import com.example.alhanoufalghayhab_codingdojo_notesapproom.Rooms.EntityNote
import com.example.alhanoufalghayhab_codingdojo_notesapproom.databinding.RecNotesViewBinding
import org.w3c.dom.Entity

class RecNotes(val listOfNotes:List<EntityNote>, val activity: Context): RecyclerView.Adapter<RecNotes.RecNotesView>() {
    inner class RecNotesView(val Binding: RecNotesViewBinding) : RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecNotesView {
        return RecNotesView(RecNotesViewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }


    override fun onBindViewHolder(holder: RecNotesView, position: Int) {
        holder.Binding.apply {
            notebody.text = listOfNotes[position].note


            notebody.setOnClickListener {
                val intent= Intent(activity, MainActivity::class.java) //context we got from constructor
                intent.putExtra("note",listOfNotes[position].note)
                intent.putExtra("pk","${listOfNotes[position].pk}")
                activity.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}