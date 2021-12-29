package com.example.alhanoufalghayhab_codingdojo_2in1app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberActivity(val numberGuess: ArrayList<String>):RecyclerView.Adapter<NumberActivity.viewHolder>() {

    inner class viewHolder(numberView: View):RecyclerView.ViewHolder(numberView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guess_number,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val number = numberGuess[position]
        var Holder =  holder.itemView.findViewById<TextView>(R.id.numberg)

        Holder.text = number.toString()


        if(Holder.text.contains("incorrect"))
        {
            // Holder.text = number.toString()
            Holder.setTextColor(Color.RED)

        }

        else if (Holder.text.contains("greate"))

        {
            //Holder.text = number.toString()
            Holder.setTextColor(Color.GREEN)

        }
    }

    override fun getItemCount(): Int {
        return numberGuess.size

    }
}
