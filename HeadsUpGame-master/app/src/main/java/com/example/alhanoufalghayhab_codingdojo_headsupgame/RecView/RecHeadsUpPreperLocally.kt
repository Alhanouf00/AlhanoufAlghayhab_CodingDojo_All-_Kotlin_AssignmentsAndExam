package com.example.alhanoufalghayhab_codingdojo_headsupgame.RecView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.HeadsUpLocalData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.RecViewHeadsupPrepLocalBinding

class RecHeadsUpPreperLocally(val listOfCelebrity :ArrayList<HeadsUpLocalData>):RecyclerView.Adapter<RecHeadsUpPreperLocally.RecHeadsUpPreperLocallyView>() {
    inner class RecHeadsUpPreperLocallyView(val Binding: RecViewHeadsupPrepLocalBinding) : RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHeadsUpPreperLocallyView {
        return RecHeadsUpPreperLocallyView(RecViewHeadsupPrepLocalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: RecHeadsUpPreperLocallyView, position: Int) {
        holder.Binding.apply {
            namelocal.text = listOfCelebrity[position].Name
            taboo1local.text = listOfCelebrity[position].taboo1
            taboo2local.text = listOfCelebrity[position].taboo2
            taboo3local.text = listOfCelebrity[position].taboo3
        }
    }


    override fun getItemCount(): Int {
        return listOfCelebrity.size
    }
}