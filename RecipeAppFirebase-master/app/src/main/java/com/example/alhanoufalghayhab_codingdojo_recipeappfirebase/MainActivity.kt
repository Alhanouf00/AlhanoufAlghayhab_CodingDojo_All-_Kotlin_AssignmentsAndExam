package com.example.alhanoufalghayhab_codingdojo_recipeappfirebase

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.Models.RecipeDataItem
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.RecView.RecipRecViewAdapter
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val database = Firebase.database
    val myRecipData = database.getReference("Recipes")


    lateinit var recipRec: RecyclerView
    val listOfRecip = ArrayList<RecipeDataItem>()
    val listOfKeys = ArrayList<String>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipRec = findViewById(R.id.recip_rec)
        recipRec.layoutManager = LinearLayoutManager(this)
        GetRecipData()
        showRcipInDialog()
        ClickToAdd()

    }

    fun ClickToAdd() {
        binding.button.setOnClickListener {
            addNewRecip()
        }
    }

    fun addNewRecip()
    {
        var uuidForRecip = UUID.randomUUID().toString()
        var newRecip = RecipeDataItem()

        val alertShow = AlertDialog.Builder(this)
        val viewShow = layoutInflater.inflate(R.layout.add_recip, null)

        var titleText = viewShow.findViewById<TextInputEditText>(R.id.title_text).text
        var authorText = viewShow.findViewById<TextInputEditText>(R.id.author_text).text

        var ingredientsText = viewShow.findViewById<TextInputEditText>(R.id.ingredients_text).text
        var instructionsText = viewShow.findViewById<TextInputEditText>(R.id.instructions_text).text
        alertShow.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which ->

            newRecip = RecipeDataItem(
                titleText.toString(),
                authorText.toString(),
                ingredientsText.toString(),
                instructionsText.toString(),
                uuidForRecip
            )


            myRecipData.child(uuidForRecip).setValue(newRecip)
            GetRecipData()
            listOfRecip.clear()
            listOfKeys.clear()


        })
        alertShow.setView(viewShow)
        alertShow.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        alertShow.create()
        alertShow.show()

    }

    fun GetRecipData()
    {
        //Log.d("123test", "Value is: " + listOfRecip)
        //Log.d("123keys", "Value is: " + listOfKeys)

        var value = RecipeDataItem()
        myRecipData.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (snapshot.exists()) {
                     var valueKey = snapshot.getValue() as HashMap<*, *>
                    for(key in valueKey.keys) {
                        listOfKeys.add(key.toString())
                       Log.d("123keys", "Value is: " + key)
                    }
                    valueKey.clear()

                    for(i in 0..(listOfKeys.size-1)) {

                        value = snapshot.child(listOfKeys[i]).getValue<RecipeDataItem>()!!
                        if (value != null) {
                            listOfRecip.add(value)
                            Log.d("123test", "Value is: " + value)
                            value = RecipeDataItem()
                        }


                    }
                    recipRec.adapter = RecipRecViewAdapter(listOfRecip,this@MainActivity)
                    recipRec.adapter!!.notifyDataSetChanged()


                }


            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("456", "Failed to read value.", error.toException())
            }


        })


    }

    @Override
    fun showRcipInDialog() {
        var updateRecip = RecipeDataItem()
        var ingredientsTextField = intent.extras?.get("ing").toString()
        var instructionsTextField = intent.extras?.get("ins").toString()
        var titleTextField = intent.extras?.get("title").toString()
        var authorTextField = intent.extras?.get("author").toString()
        var uuidValu = intent.extras?.get("uuid").toString()

        val alertShow = AlertDialog.Builder(this)
        alertShow.setTitle("Recipes")
        val viewShow = layoutInflater.inflate(R.layout.show_recip, null)
        var titleText = viewShow.findViewById<EditText>(R.id.title)
        var authorText = viewShow.findViewById<EditText>(R.id.author)
        var ingredientsText = viewShow.findViewById<EditText>(R.id.ingredients)
        var instructionsText = viewShow.findViewById<EditText>(R.id.instructions)


        titleText.hint = titleTextField
        authorText.hint = authorTextField
        ingredientsText.hint = ingredientsTextField
        instructionsText.hint = instructionsTextField


        alertShow.setPositiveButton("Edit",
            DialogInterface.OnClickListener { dialog, which ->


                updateRecip = RecipeDataItem(
                    titleText.text.toString(),
                    authorText.text.toString(),
                    ingredientsText.text.toString(),
                    instructionsText.text.toString(),
                    uuidValu

                )
                myRecipData.child(uuidValu).setValue(updateRecip)
                GetRecipData()
            })

        alertShow.setView(viewShow)
        alertShow.setNegativeButton(
            "Delete",
            DialogInterface.OnClickListener { dialog, id ->
                myRecipData.child(uuidValu).removeValue()
                GetRecipData()



            })
        alertShow.create()
        alertShow.show()

    }






}
