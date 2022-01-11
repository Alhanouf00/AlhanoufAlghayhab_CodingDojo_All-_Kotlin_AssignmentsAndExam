package com.example.alhanoufalghayhab_codingdojo_topapps.RecView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_topapps.Model.TopAppData
import com.example.alhanoufalghayhab_codingdojo_topapps.databinding.Top100ViewBinding

class RecTop100(val listOfApp:ArrayList<TopAppData>): RecyclerView.Adapter<RecTop100.RecTop100View>() {
    inner class RecTop100View(val Binding: Top100ViewBinding): RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTop100View {
        return RecTop100View(Top100ViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecTop100View, position: Int) {
        holder.Binding.apply {
            name100.text = listOfApp[position].nameTop
        }
    }

    override fun getItemCount(): Int {
        return listOfApp.size
    }
}