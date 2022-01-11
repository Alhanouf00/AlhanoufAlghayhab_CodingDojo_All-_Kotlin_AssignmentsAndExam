package com.example.alhanoufalghayhab_codingdojo_xmlformat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_xmlformat.databinding.StudentListBinding
import com.example.alhanoufalghayhab_codingdojo_xmlformat.model.XMLData

class RecStudent(val listOfStudents: ArrayList<XMLData>):RecyclerView.Adapter<RecStudent.RecStudentView>() {

    inner class RecStudentView(val Binding:StudentListBinding):RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecStudentView {
        return RecStudentView(StudentListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: RecStudentView, position: Int) {
       holder.Binding.apply {
           name.text = listOfStudents[position].name
           grade.text = listOfStudents[position].grade.toString()

       }
    }


    override fun getItemCount(): Int {
       return listOfStudents.size
    }
}