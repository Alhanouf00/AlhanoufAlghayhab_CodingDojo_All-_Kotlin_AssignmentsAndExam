package com.example.alhanoufalghayhab_codingdojo_todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val addToDo= ArrayList<String>()
    lateinit var AddButton: View
    lateinit var recToDo: RecyclerView
    lateinit var deleteButton: View
    var holdSize =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recToDo = findViewById(R.id.recTo)

        recToDo.layoutManager = LinearLayoutManager(this)
        Task()

        Delete()
    }

    fun Task() {



        AddButton = findViewById<Button>(R.id.addbutton)

        AddButton.setOnClickListener { view -> showdialog()

            holdSize = addToDo.size

            //Toast.makeText(this,"You are delete $holdSize tasks", Toast.LENGTH_SHORT).show()

        }

    }


    fun Delete()
    {

        deleteButton = findViewById<ImageButton>(R.id.imagebutton)
        deleteButton.setOnClickListener {view->
            Toast.makeText(this,"You are delete $holdSize tasks", Toast.LENGTH_SHORT).show()
            addToDo.clear()
            recToDo.adapter = ToDoList(addToDo)

        }
    }

    fun showdialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("To do list")


        val input = EditText(this)
        input.setHint("Enter your task")
        builder.setView(input)

        builder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, id ->
            var toDoText = input.text.toString()
            addToDo.add(toDoText)
            recToDo.adapter = ToDoList(addToDo)
            recToDo.adapter!!.notifyDataSetChanged()

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        builder.create()
        builder.show()
    }



}