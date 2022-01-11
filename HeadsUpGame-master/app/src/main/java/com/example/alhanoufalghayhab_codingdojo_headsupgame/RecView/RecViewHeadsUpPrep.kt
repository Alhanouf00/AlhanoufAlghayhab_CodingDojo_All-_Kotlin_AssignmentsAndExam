package com.example.alhanoufalghayhab_codingdojo_headsupgame.RecView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.CelebrityListData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.RecViewHeadsupPrepBinding

class RecViewHeadsUpPrep(val listCele:ArrayList<CelebrityListData>): RecyclerView.Adapter<RecViewHeadsUpPrep.RecViewHeadsUpPrepView>() {
    inner class RecViewHeadsUpPrepView(val Binding: RecViewHeadsupPrepBinding):RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHeadsUpPrepView {
        return RecViewHeadsUpPrepView(RecViewHeadsupPrepBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }


    override fun onBindViewHolder(holder: RecViewHeadsUpPrepView, position: Int) {

        holder.Binding.apply {
            name.text = listCele[position].name
            taboo1.text = listCele[position].taboo1
            taboo2.text = listCele[position].taboo2
            taboo3.text = listCele[position].taboo3


        }


    }


    override fun getItemCount(): Int {
        return listCele.size
    }
}