package com.example.alhanoufalghayhab_codingdojo_headsupgame.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.HeadsUpLocalData

val DATABASENAME = "HeadsUpGame"
val TABLENAME = "HeadsUp"
val COL_PK = "pk"
val COL_Name = "name"
val COL_taboo1 = "taboo1"
val COL_taboo2 = "taboo2"
val COL_taboo3 = "taboo3"




class HeadsUpDataHelper(val context: Context): SQLiteOpenHelper(context,DATABASENAME,null,4) {
    val sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE HeadsUp (pk INTEGER PRIMARY KEY AUTOINCREMENT,Name text,taboo1 text,taboo2 text,taboo3 text)")

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS HeadsUp")
        db?.execSQL("DROP TABLE IF EXISTS UsersNotes")
        onCreate(db)
    }

    fun saveData(name: String,taboo1:String,taboo2:String,taboo3:String){
        val contentValues = ContentValues()
        contentValues.put("Name", name)
        contentValues.put("taboo1",taboo1)
        contentValues.put("taboo2",taboo2)
        contentValues.put("taboo3",taboo3)

        sqLiteDatabase.insert("HeadsUp", null, contentValues)
    }


    fun readData(): ArrayList<HeadsUpLocalData>{
        val notes = ArrayList<HeadsUpLocalData>()
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM HeadsUp", null)

        if(cursor.count < 1){
            println("No Data Found")
        }else{
            while(cursor.moveToNext()){

                val pk = cursor.getInt(0)
                val name = cursor.getString(1)
                val taboo1 = cursor.getString(2)
                val taboo2 = cursor.getString(3)
                val taboo3 = cursor.getString(4)
                notes.add(HeadsUpLocalData(name,taboo1,taboo2,taboo3))
            }
        }
        return notes
    }





}
