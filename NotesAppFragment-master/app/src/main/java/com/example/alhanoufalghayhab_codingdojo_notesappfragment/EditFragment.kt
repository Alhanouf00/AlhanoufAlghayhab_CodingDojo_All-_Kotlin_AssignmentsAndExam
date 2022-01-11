package com.example.alhanoufalghayhab_codingdojo_notesappfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.databinding.FragmentEditBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EditFragment : Fragment() {
    lateinit var Bindings: FragmentEditBinding

    val db = Firebase.firestore
    var notHint: Any? =null
    var pk : Any? =null
    var update: HomeFragment?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Bindings =FragmentEditBinding.inflate(inflater, container, false)
        buttonEdit()
       buttonDelete()

        return Bindings.root

    }

    fun buttonEdit()
    {
        Bindings.edit.setOnClickListener {

            Bindings.notetextfeild.hint = notHint.toString()

            val editText = Bindings.notetextfeild.text
            val pkText = Bindings.pktextfeild.text

            db.collection("FragmentNotes").document("${pkText.toString()}").update("${pkText.toString()}",editText.toString())

            update?.retrieveNotes()

        }
    }

    fun buttonDelete()
    {
        Bindings.delete.setOnClickListener {
            val pkText = Bindings.pktextfeild.text
            db.collection("FragmentNotes").document("${pkText.toString()}").delete()
            update?.retrieveNotes()
        }
    }
}