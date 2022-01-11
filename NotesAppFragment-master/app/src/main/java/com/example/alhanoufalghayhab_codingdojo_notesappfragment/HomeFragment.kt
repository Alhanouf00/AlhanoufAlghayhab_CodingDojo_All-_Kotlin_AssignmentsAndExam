package com.example.alhanoufalghayhab_codingdojo_notesappfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.Models.NotesData
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.R
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.RecView.RecNotesApp
import com.example.alhanoufalghayhab_codingdojo_notesappfragment.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    lateinit var Bindings: FragmentHomeBinding
    lateinit var recViewNotesFragment: RecyclerView
    val db = Firebase.firestore
    val noteData = ArrayList<NotesData>()
    var pk = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Bindings = FragmentHomeBinding.inflate(inflater, container, false)
        buttonAdd()
       retrieveNotes()
        anotherFragment()
        recViewNotesFragment = Bindings.recviewnotefragment
        recViewNotesFragment.layoutManager = LinearLayoutManager(requireActivity())
        // Inflate the layout for this fragment
        return Bindings.root

    }

    fun buttonAdd() {
        Bindings.addfragment.setOnClickListener {
            var noteSend = Bindings.notetextfieldfragment.text
            val noteUser = hashMapOf(
                "$pk" to "${noteSend.toString()}",
            )
            db.collection("FragmentNotes").document("$pk")
                .set(noteUser)
                .addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot successfully written")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error writing document", e)
                }
            retrieveNotes()
            pk++

        }
    }

    fun retrieveNotes() {
        db.collection("FragmentNotes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    for (value in document.data.values) {
                        noteData.add(NotesData(document.id.toInt(), value.toString()))
                        Log.d("", "${document.data}")
                        recViewNotesFragment.adapter = RecNotesApp(noteData,requireActivity() )
                        recViewNotesFragment.adapter!!.notifyDataSetChanged()
                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
        noteData.clear()
    }
    fun anotherFragment()

    {
        Bindings.editfragment.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editFragment2)

        }
    }


}