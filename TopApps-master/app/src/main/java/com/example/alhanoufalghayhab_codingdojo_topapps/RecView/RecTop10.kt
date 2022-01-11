package com.example.alhanoufalghayhab_codingdojo_topapps.RecView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_topapps.Model.TopAppData
import com.example.alhanoufalghayhab_codingdojo_topapps.databinding.Top10ViewBinding

class RecTop10(val listOfApp:ArrayList<TopAppData>):RecyclerView.Adapter<RecTop10.RecTop10View>() {
    inner class RecTop10View(val Binding:Top10ViewBinding):RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTop10View {
        return RecTop10View(Top10ViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecTop10View, position: Int) {
        holder.Binding.apply {
            name10.text = listOfApp[position].nameTop
        }
    }

    override fun getItemCount(): Int {
        return listOfApp.size
    }
}