package com.example.alhanoufalghayhab_codingdojo_authorizationuser.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.Models.UsersData

val DATABASENAME = "Auth DATABASE2"
val TABLENAME = "Users"
val COL_Name = "Name"
val COL_Email = "Email"
val COL_Password = "Password"

class AuthUserDataBaseHelper(val context: Context): SQLiteOpenHelper(context,DATABASENAME,null,6) {
    val sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE Users (Email String PRIMARY KEY ,Name text,Password text)")

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }
    fun saveData(name: String,email:String,password:String):Boolean
    {
        val contentValues = ContentValues()
        contentValues.put("Name", name)
        contentValues.put("Email", email)
        contentValues.put("Password", password)
        val holdResult = sqLiteDatabase.insert("Users", null, contentValues)

        if (holdResult.toInt() == -1 )
        {
            return false
        }
        return true
    }

    fun readData(email:String,passwordUser:String):UsersData{
        var retriveuser = UsersData("","")
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE Email = '$email' ", null)
        if(cursor.count < 1){
            println("No Data Found")
        }else{
            while(cursor.moveToNext()){
                val emial1 = cursor.getString(0)
                val name =  cursor.getString(1)
                val passwor = cursor.getString(2)
                Log.d("Password","$passwor")
                if (passwor == passwordUser)
                {
                    retriveuser = UsersData(emial1,name)
                }
            }
        }
        return retriveuser
    }
}
