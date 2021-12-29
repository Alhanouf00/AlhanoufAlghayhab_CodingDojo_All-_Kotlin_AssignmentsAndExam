package com.example.alhanoufalghayhab_codingdojo_recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recview (val listItem: ArrayList<String>) :RecyclerView.Adapter<recview.viewHolder>() {

    inner class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return viewHolder(view)
    }


    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = listItem[position]
       var Holder =  holder.itemView.findViewById<TextView>(R.id.itemhere)
        Holder.text = item
        //holder.itemView.apply { itemhere.text = listItem[position].itemList }

    }


}