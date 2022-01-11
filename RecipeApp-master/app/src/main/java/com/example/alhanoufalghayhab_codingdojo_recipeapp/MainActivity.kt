package com.example.alhanoufalghayhab_codingdojo_recipeapp

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView


import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_recipeapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

import com.google.gson.GsonBuilder

import com.google.gson.Gson
import org.json.JSONException

import org.json.JSONObject







class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    lateinit var APIRecipRec: RecyclerView
    val listOfRecip = ArrayList<RecipeAPIDataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APIRecipRec = findViewById(R.id.recip_rec)
        APIRecipRec.layoutManager = LinearLayoutManager(this)
        GetRecipData()
        ReciveClickItem()
        ClickTAdd()

    }

    fun GetRecipData() {


        var reciveRecipData = ClientAPI().featchRecipData()?.create(InterfaceAPI::class.java)

        var reciveRecip = reciveRecipData?.getRecipdata()

        if (reciveRecip != null) {

            reciveRecip.enqueue(object : Callback<ArrayList<RecipeAPIDataItem>> {
                override fun onResponse(
                    call: Call<ArrayList<RecipeAPIDataItem>>,
                    response: Response<ArrayList<RecipeAPIDataItem>>
                ) {
                    var respon = response.body()

                    if (respon != null) {
                        for (i in respon) {
                            listOfRecip.add(i)
                            APIRecipRec.adapter = RecipRecViewAdapter(listOfRecip,this@MainActivity)
                            APIRecipRec.adapter?.notifyDataSetChanged()
                        }
                    }

                }

                override fun onFailure(call: Call<ArrayList<RecipeAPIDataItem>>, t: Throwable) {
                    Log.d("respoE", "${t.message}")
                }
            })

        }
    }


    fun ClickTAdd() {
        binding.button.setOnClickListener {

            addNewRecip()
        }
    }

    fun addNewRecip()
    {
        var newRecip = RecipeAPIDataItem("","","","")
        var sendNewRecip = ClientAPI().featchRecipData()?.create(InterfaceAPI::class.java)

        val alertShow = AlertDialog.Builder(this)
        val viewShow = layoutInflater.inflate(R.layout.add_recip, null)

        var titleText = viewShow.findViewById<TextInputEditText>(R.id.title_text).text
        var authorText = viewShow.findViewById<TextInputEditText>(R.id.author_text).text

        var ingredientsText = viewShow.findViewById<TextInputEditText>(R.id.ingredients_text).text
        var instructionsText = viewShow.findViewById<TextInputEditText>(R.id.instructions_text).text

        Log.d("Recipee", "${newRecip}")
        alertShow.setPositiveButton("Add",DialogInterface.OnClickListener { dialog, which ->

            newRecip = RecipeAPIDataItem(
                titleText.toString(),
                authorText.toString(),
                ingredientsText.toString(),
                instructionsText.toString()
            )


        var sendRecip = sendNewRecip?.postRecipdata(newRecip)
            Log.d("Recipe", "${sendRecip}")

        if (sendRecip!=null)
        {

            Log.d("Recipee", "${titleText}")
            Log.d("Recipee", "${authorText}")
            Log.d("Recipee", "${ingredientsText}")
            Log.d("Recipee", "${instructionsText}")
         //   Log.d("Recipee", "${newRecip}")
            //Log.d("Recipe", "${sendRecip}")

            sendRecip.enqueue(object : Callback<ArrayList<RecipeAPIDataItem>?> {
                override fun onResponse(
                    call: Call<ArrayList<RecipeAPIDataItem>?>,
                    response: Response<ArrayList<RecipeAPIDataItem>?>
                ) {
                    if (response.code() == 200)
                    {
                        Log.d("Suess", "${response.body()}")


                    }

                    else {

                        Log.d("Suess", "${response.isSuccessful}")
                        Log.d("Suess", "${response.errorBody().toString()}")
                        Log.d("Suess", "${response.message()}")
                        Log.d("Suess", "${response.code()}")
                    }

                   /* if (response.code() === 400) {
                        if (!response.isSuccessful) {
                            var jsonObject: JSONObject? = null
                            try {
                                jsonObject = JSONObject(response.errorBody()!!.string())
                                val userMessage = jsonObject.getString("userMessage")
                                val internalMessage = jsonObject.getString("internalMessage")
                                val errorCode = jsonObject.getString("errorCode")
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    */


                }

                override fun onFailure(call: Call<ArrayList<RecipeAPIDataItem>?>, t: Throwable) {
                    Log.d("Error", "${t.message}")
                }
            })

        } })
        alertShow.setView(viewShow)
        alertShow.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        alertShow.create()
        alertShow.show()

    }



    @Override
        fun ReciveClickItem() {


            var ingredients = intent.extras?.get("ing")
            var instructions = intent.extras?.get("ins")
            val alertShow = AlertDialog.Builder(this)
            val viewShow = layoutInflater.inflate(R.layout.show_recip, null)
            var ingredientsText = viewShow.findViewById<TextView>(R.id.ingredients)
            var instructionsText = viewShow.findViewById<TextView>(R.id.instructions)
            ingredientsText.text = ingredients.toString()
            instructionsText.text = instructions.toString()
            alertShow.setView(viewShow)
            alertShow.setNegativeButton(
                "Back",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            alertShow.create()
            alertShow.show()

        }
    }

