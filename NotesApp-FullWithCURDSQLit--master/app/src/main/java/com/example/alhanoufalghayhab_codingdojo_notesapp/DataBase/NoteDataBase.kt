package com.example.alhanoufalghayhab_codingdojo_notesapp.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.alhanoufalghayhab_codingdojo_notesapp.Models.NotesData

val DATABASENAME = "Note DATABASE"
val TABLENAME = "UsersNotes"
val COL_Notes = "notes"
val COL_PK = "pk"

class NoteDataBase(val context: Context):SQLiteOpenHelper(context,DATABASENAME,null,3) {
     val sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {

            db?.execSQL("CREATE TABLE UsersNotes (pk INTEGER PRIMARY KEY AUTOINCREMENT,Notes text)")

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS UsersNotes")
            onCreate(db)
    }

    fun saveData(note: String){
        val contentValues = ContentValues()
        contentValues.put("Notes", note)

        sqLiteDatabase.insert("UsersNotes", "notes", contentValues)
    }
    fun readData(): ArrayList<NotesData>{
        val notes = ArrayList<NotesData>()
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM UsersNotes", null)

        if(cursor.count < 1){
            println("No Data Found")
        }else{
            while(cursor.moveToNext()){
                cursor.getColumnIndex("pk")
                Log.d("curs","${cursor.getColumnIndex("pk")}")
                val pk = cursor.getInt(0)
                val note = cursor.getString(1)
                notes.add(NotesData(pk,note))
            }
        }
        return notes
    }
    fun updateNote(note: NotesData)
    {
        val contentValues = ContentValues()
        contentValues.put("Notes", note.notes)

        Log.d("123","${note.notes}")
        sqLiteDatabase.update("UsersNotes",contentValues,"pk = ${note.pk}", null)
    }

    fun deleteNote(note: NotesData){
        sqLiteDatabase.delete("UsersNotes", "pk = ${note.pk}", null)
    }





}