package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppFragments.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.alhanoufalghayhab_codingdojo_noteappweek7.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FragmentNotesEdit:Fragment(){
    val db = Firebase.firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      var holdview:View =  inflater.inflate(R.layout.fragment_edit_notes,container,false)

       var editButton =  holdview.findViewById<Button>(R.id.edit)
        var noteField = holdview.findViewById<EditText>(R.id.notetextfeild)



        editButton.setOnClickListener {
           var noteText = noteField.text
            //db.collection("NotesUsers").document("$pk").update("$pk",notText.toString())
            //    .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
              //  .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
        }





        return view


    }
}