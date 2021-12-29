package com.example.alhanoufalghyhab_codingdojo_exam_mathstudyapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.alhanoufalghyhab_codingdojo_exam_mathstudyapp.databinding.MathEquBinding

class MathRecView(val resultEq: ArrayList<String>):RecyclerView.Adapter<MathRecView.mathViewHolder>() {
    inner class mathViewHolder (val binding: MathEquBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mathViewHolder {
        return mathViewHolder(MathEquBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: mathViewHolder, position: Int) {
        val equMath = resultEq[position]
        holder.binding.apply{textdisplay.text = equMath.toString()}
    }

    override fun getItemCount() = resultEq.size
}