package com.example.alhanoufalghayhab_codingdojo_postrequestpractice

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_postrequestpractice.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    lateinit var APIDataRec: RecyclerView
    var ListOfPK = ArrayList<UpdateData>()
    val ListOfAPI= ArrayList<DataAPIItem>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        APIDataRec = findViewById(R.id.recview)
        APIDataRec.layoutManager = LinearLayoutManager(this)
        GetAPIData()
        postData()
        getpk()
        //clickToUpdate()
        updateData()

    }

     fun GetAPIData() {
        var reciveAPIData = Client().featchData()?.create(APIInterface::class.java)

        var recive = reciveAPIData?.getdata()

        if (recive != null) {

                recive.enqueue(object : Callback<ArrayList<DataAPIItem>> {
                    override fun onResponse(
                        call: Call<ArrayList<DataAPIItem>>,
                        response: Response<ArrayList<DataAPIItem>>
                    ) {
                        var respon = response.body()
                        if (respon != null) {
                            for (i in respon)
                            {

                                ListOfAPI.add(i)

                                APIDataRec.adapter = RecViewAdapter(ListOfAPI,ListOfPK,this@MainActivity)
                                APIDataRec.adapter?.notifyDataSetChanged()
                            }
                        }

                    }
                    override fun onFailure(call: Call<ArrayList<DataAPIItem>>, t: Throwable) {
                        Log.d("respoE", "${t.message}")
                    }
                })

        }
    }

     fun postData()
    {
        binding.add.setOnClickListener {
                showdialog()
    }}

    fun showdialog(){
        var sendData = Client().featchData()?.create(APIInterface::class.java)
        var newUser = DataAPIItem("","")
        var bodyOfDilog = LinearLayout(this)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("New User")
        val name = EditText(this)
        val location = EditText(this)
        name.setHint("Enter your name")
        location.setHint("Enter your location")
        bodyOfDilog.orientation
        bodyOfDilog.addView(name)
        bodyOfDilog.addView(location)
        builder.setView(bodyOfDilog)
        builder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, id ->
            var nameText = name.text.toString()
            var locationText = location.text.toString()
            newUser = DataAPIItem(nameText,locationText)
                var Send = sendData?.postdata(newUser)
                if (Send != null) {
                    Send.enqueue(object : Callback<ArrayList<DataAPIItem>?> {
                        override fun onResponse(
                            call: Call<ArrayList<DataAPIItem>?>,
                            response: Response<ArrayList<DataAPIItem>?>
                        ) {
                            Log.d("Suess", "${response.isSuccessful}")
                            response.body()
                            APIDataRec.adapter!!.notifyDataSetChanged()

                        }
                        override fun onFailure(call: Call<ArrayList<DataAPIItem>?>, t: Throwable) {
                            Log.d("Error", "${t.message}")
                        }
                    })
                }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create()
        builder.show()
    }

    fun getpk()
    {
        var updateData = Client().featchData()?.create(APIInterface::class.java)
        var update = updateData?.getpk()
        if (update != null) {
            update.enqueue(object : Callback<ArrayList<UpdateData>?> {
                override fun onResponse(
                    call: Call<ArrayList<UpdateData>?>,
                    response: Response<ArrayList<UpdateData>?>
                ) {

                    var respon = response.body()
                    if (respon != null) {
                        for (i in respon)
                        {
                            ListOfPK.add(i)
                            APIDataRec.adapter = RecViewAdapter(ListOfAPI,ListOfPK,this@MainActivity)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<UpdateData>?>, t: Throwable) {
                    Log.d("Error", "${t.message}")
                }
            })
        }

    }


    /*
    fun clickToUpdate()
    {
        binding.dochange.setOnClickListener { updateData() }

    }


     */

    fun updateData()
    {
        var updateUserCall = Client().featchData()?.create(APIInterface::class.java)
        var updateUser = DataAPIItem("","")
        var holdPkUser = 0

        val alertShow = AlertDialog.Builder(this)
        val viewShow = layoutInflater.inflate(R.layout.to_update_user, null)

        var pkUser = viewShow.findViewById<TextView>(R.id.pk)

        var nameHint = viewShow.findViewById<TextInputLayout>(R.id.input1)
        var locationHint = viewShow.findViewById<TextInputLayout>(R.id.input2)


        var nameText = viewShow.findViewById<TextInputEditText>(R.id.name_update)
        var locationText = viewShow.findViewById<TextInputEditText>(R.id.location_update)

        nameHint.hint = intent.extras?.get("name").toString()
        locationHint.hint = intent.extras?.get("location").toString()
        pkUser.text = intent.extras?.get("PK").toString()

        alertShow.setPositiveButton("Edite",DialogInterface.OnClickListener { dialog, which ->
            holdPkUser = intent.extras?.get("PK").toString().toInt()
            updateUser = DataAPIItem(locationText.text.toString(),nameText.text.toString())


            var updaters = updateUserCall?.updatedata(holdPkUser,updateUser)
            Log.d("Updaters","${updateUserCall?.updatedata(holdPkUser,updateUser)}")
            if (updaters != null) {
                updaters.enqueue(object : Callback<ArrayList<UpdateData>?> {
                    override fun onResponse(
                        call: Call<ArrayList<UpdateData>?>,
                        response: Response<ArrayList<UpdateData>?>
                    ) {
                        if (response.code()==200)
                        {
                            Log.d("updatsu","${response.isSuccessful}")
                            APIDataRec.adapter!!.notifyDataSetChanged()

                        }

                        else
                        {
                            Log.d("updatsu","${response.body()}")
                            Log.d("updatsu","${response.errorBody()}")
                            Log.d("updatsu","${response.code()}")
                            Log.d("updatsu","${response.message()}")


                        }
                    }

                    override fun onFailure(call: Call<ArrayList<UpdateData>?>, t: Throwable) {
                        Log.d("Error", "${t.message}")
                    }
                })
            }
        })

        alertShow.setNeutralButton("Delete",DialogInterface.OnClickListener { dialog, which ->
            holdPkUser = intent.extras?.get("PK").toString().toInt()
            var deleteUser = updateUserCall?.deletedata(holdPkUser)

            Log.d("Delete","${holdPkUser}")


            if (deleteUser != null) {
                deleteUser.enqueue(object: Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {

                        if (response.code()==200) {
                            Log.d("dupdatsu","${response.isSuccessful}")
                            APIDataRec.adapter!!.notifyDataSetChanged()

                        } else {
                            Log.d("dupdatsu","${response.body()}")
                            Log.d("dupdatsu","${response.errorBody()}")
                            Log.d("dupdatsu","${response.code()}")
                            Log.d("dupdatsu","${response.message()}")
                        }


                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("Error", "${t.message}")
                    }
                })
            }



        })



        alertShow.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        alertShow.setView(viewShow)
        alertShow.create()
        alertShow.show()


    }





}