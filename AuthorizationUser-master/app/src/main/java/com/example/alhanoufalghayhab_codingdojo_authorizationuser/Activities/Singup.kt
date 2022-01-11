package com.example.alhanoufalghayhab_codingdojo_authorizationuser.Activities

import android.content.DialogInterface
import android.content.Intent
import android.media.AudioMetadata
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.Database.AuthUserDataBaseHelper
import com.example.alhanoufalghayhab_codingdojo_authorizationuser.databinding.ActivitySingupBinding
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import kotlin.text.Charsets.UTF_8

class Singup : AppCompatActivity() {
    lateinit var Binding: ActivitySingupBinding
    val databaseUserHelper by lazy { AuthUserDataBaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        addNewUser()
    }
    fun addNewUser()
    {
        Binding.singupbtn.setOnClickListener {

            var name = Binding.nametext.text
            var email = Binding.emailtext.text
            var password = Binding.passwordtext.text

            val encryptPassword = MessageDigest.getInstance("MD5")

            val format = BigInteger(1, encryptPassword.digest(password.toString().toByteArray())).toString(16).padStart(32, '0')


            Log.d("123","${format}")


            if (!name.isNullOrEmpty()&&!email.isNullOrEmpty()&&!password.isNullOrEmpty())

            {
                val holdResult = databaseUserHelper.saveData(name.toString(), email.toString(), format.toString())
                if (holdResult == false)
                {
                    showAlert()
                }
                else
                {
                    Toast.makeText(this,"The user create successfully",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    fun showAlert()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Sing up error")
        alert.setMessage("the email is already exist do you want to login?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@Singup, Login::class.java)
            startActivity(intent)
        })
        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        alert.create()
        alert.show()

    }

}

