package com.example.alhanoufalghayhab_codingdojo_studyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_studyapp.databinding.StudyAppBinding


class Study(val studySummary: MutableMap<String,String>):RecyclerView.Adapter<Study.StudyViewHolder>() {
    inner class StudyViewHolder(val binding: StudyAppBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {

        return StudyViewHolder(
            StudyAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        val subjectArray = ArrayList<String>()
        val summaryArray = ArrayList<String>()


        for (key in studySummary.keys)
        {
            subjectArray.add(key.toString())
            summaryArray.add( studySummary[key].toString())

        }


        println("sizeof  ${studySummary.size}")


        holder.binding.apply {

                supject.text = subjectArray[position]
                summary.text = summaryArray[position]


        }
    }

    override fun getItemCount(): Int
    {
        return studySummary.size
    }
}