package com.example.alhanoufalghayhab_codingdojo_debuggingchallenge2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val listPosition: TextView = itemView.findViewById(R.id.itemNumber)
    val listCountry: TextView = itemView.findViewById(R.id.Country)
    val listCapital: TextView = itemView.findViewById(R.id.Capital)
}