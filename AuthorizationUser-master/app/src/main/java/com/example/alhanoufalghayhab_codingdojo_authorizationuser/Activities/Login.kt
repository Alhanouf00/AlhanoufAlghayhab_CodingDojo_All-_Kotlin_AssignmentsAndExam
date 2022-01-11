package com.example.alhanoufalghayhab_codingdojo_authorizationuser.Activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.Database.AuthUserDataBaseHelper
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.databinding.ActivityLoginBinding
import java.math.BigInteger
import java.security.MessageDigest

class Login : AppCompatActivity() {
    lateinit var Binding: ActivityLoginBinding
    val databaseUserHelper by lazy { AuthUserDataBaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        logUser()
    }
    fun logUser()
    {
        Binding.loginbtn.setOnClickListener {
            val userEmail = Binding.emailtextlogin.text
            val password = Binding.passwordtextlogin.text

            val encryptPassword = MessageDigest.getInstance("MD5")

            val format = BigInteger(1, encryptPassword.digest(password.toString().toByteArray())).toString(16).padStart(32, '0')
            val hold = databaseUserHelper.readData(userEmail.toString(),format)
            Log.d("Login","${hold}")
            if(hold.email != "")
            {
                val intent = Intent(this@Login, Profile::class.java)
                intent.putExtra("name",hold.name)
                intent.putExtra("email",hold.email)
                startActivity(intent)
            }
            else
            {
                showDialog()

            }
        }

    }

    fun showDialog()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Login error")
        alert.setMessage("Error Password ")
        alert.setPositiveButton("Reenter the password", DialogInterface.OnClickListener { dialog, which -> dialog.cancel()})
        alert.create()
        alert.show()
    }

    }