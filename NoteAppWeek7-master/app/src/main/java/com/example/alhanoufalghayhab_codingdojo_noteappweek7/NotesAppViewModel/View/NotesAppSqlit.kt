package com.example.alhanoufalghayhab_codingdojo_noteappweek7.NotesAppViewModel.View

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesAppSqlit: ViewModel() {
    val noteCURD: MutableLiveData<String> = MutableLiveData()
    val notePK: MutableLiveData<Int> = MutableLiveData()


    fun addNote(note:String)
    {
        if(!note.isNullOrEmpty())

        noteCURD.postValue(note)

    }

    fun addPK(pk:Int)
    {
        notePK.postValue(pk)

    }


    fun getNotes():LiveData<String>
    {
        return noteCURD
    }

    fun getPK():LiveData<Int>
    {
        return notePK
    }

}