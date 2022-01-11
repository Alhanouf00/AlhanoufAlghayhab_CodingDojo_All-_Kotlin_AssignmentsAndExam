package com.example.alhanoufalghayhab_codingdojo_debuggingchallenge2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    lateinit var alertDialogSubmitBtn: Button
    lateinit var saveData: SharedPreferences
    var holdSaveData = saveData.edit()
    private var arrayListOfCountriesAndCapitals = arrayListOf(
        arrayListOf("Saudi Arabia", "Riyadh"),
        arrayListOf("Nigeria", "Abuja"),
        arrayListOf("Rwanda", "Kigali"),
        arrayListOf("USA", "Washington"),
        arrayListOf("China", "Beijing"),
    )

    //val arrayListOfCountriesAndCapitalss = ArrayList<ArrayList<String>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabButton = findViewById(R.id.fabBtn)
        setupRecyclerView()


        fabButton.setOnClickListener {
            val singleUserEntryList = arrayListOf<String>()

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)

            //Store user's input text to variables
            val countryText = countryET.text
            val capitalText = capitalET.text

            alertDialogSubmitBtn.setOnClickListener {
                //Add both texts to list
                if (((capitalText.isEmpty())&& countryText.toString().isEmpty()) || (countryText.toString().isEmpty()) || (capitalText.toString().isEmpty()))
                {

                    Toast.makeText(this,"Please enter a capital and country ",Toast.LENGTH_SHORT).show()
                }

                else {


                    Toast.makeText(this,"You add the country ${countryText} and his capital ${capitalText} ",Toast.LENGTH_SHORT).show()

                    singleUserEntryList.add(countryText.toString())
                    singleUserEntryList.add(capitalText.toString())

                    //Add single entry list to Global list
                    arrayListOfCountriesAndCapitals.add(singleUserEntryList)
                    alertDialog.dismiss()
                }
            }

        }
    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)

        val builder =
            AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }



    private fun setupRecyclerView() {
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter =
            ListSelectionRecyclerViewAdapter(arrayListOfCountriesAndCapitals)

    }
}
