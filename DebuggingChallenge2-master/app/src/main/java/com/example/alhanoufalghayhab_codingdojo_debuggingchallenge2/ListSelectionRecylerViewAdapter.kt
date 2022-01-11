package com.example.alhanoufalghayhab_codingdojo_debuggingchallenge2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val stateAndCapitals: ArrayList<ArrayList<String>>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position+1).toString()
        holder.listCountry.text = stateAndCapitals[position][0]
        holder.listCountry.setBackgroundColor(Color.MAGENTA)
        holder.listCapital.text = stateAndCapitals[position][1]
        holder.listCapital.setBackgroundColor(Color.MAGENTA)

    }

    override fun getItemCount(): Int {
        return stateAndCapitals.size
    }

}
