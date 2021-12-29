package com.example.alhanoufalghayhab_codingdojo_guessthephrase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GeussPhrase(val phrases:ArrayList<String>) : RecyclerView.Adapter<GeussPhrase.viewHolder>() {
    inner class viewHolder(phraseGeuss: View):RecyclerView.ViewHolder(phraseGeuss)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.geuss_phrase,parent,false)
        return viewHolder(view)    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val Phrase = phrases[position]

        var Holder =  holder.itemView.findViewById<TextView>(R.id.phrase)

        if (Phrase.contains("Wrong"))
        {
            Holder.text = Phrase
            Holder.setTextColor(Color.RED)
        }

        else if (Phrase.contains("Correct"))
        {
            Holder.text = Phrase
            Holder.setTextColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
        return phrases.size

    }
}