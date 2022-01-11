package com.example.alhanoufalghayhab_codingdojo_todoapp

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

import com.example.alhanoufalghayhab_codingdojo_todoapp.databinding.ToDoListBinding
import com.google.android.material.snackbar.Snackbar

class ToDoList(val ToDo:ArrayList<String>): RecyclerView.Adapter<ToDoList.ToDoListViewHolder>() {
    inner class ToDoListViewHolder(val Binding:ToDoListBinding):RecyclerView.ViewHolder(Binding.root)

    lateinit var messageDelete: ConstraintLayout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(
            ToDoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        var todo= ToDo[position]

            holder.Binding.apply {
                textView.text = todo.toString()
                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    textView.setTextColor(Color.GRAY)
                    if (!isChecked) {
                        textView.setTextColor(Color.parseColor("#3949AB"))
                    }

                }
            }
    }

    override fun getItemCount(): Int {
        if(ToDo.isEmpty())
        {
            return 0

        }

        else {
            return ToDo.size
        }
    }
}