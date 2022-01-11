package com.example.alhanoufalghayhab_codingdojo_headsupgame.MainActivity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_headsupgame.API.CelebrityInterface
import com.example.alhanoufalghayhab_codingdojo_headsupgame.API.ClientCelebrity
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.CelebrityListData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.ListDataWithPK
import com.example.alhanoufalghayhab_codingdojo_headsupgame.R
import com.example.alhanoufalghayhab_codingdojo_headsupgame.RecView.RecViewHeadsUpPrep
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.ActivityHeadsUpPrepBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadsUpPrep : AppCompatActivity() {
    lateinit var showDataInRecView: RecyclerView
    lateinit var Binding: ActivityHeadsUpPrepBinding
    val listOfPrep= ArrayList<CelebrityListData>()
    val listOfPrepWithPK= ArrayList<ListDataWithPK>()

    var holdPK = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityHeadsUpPrepBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        showDataInRecView = findViewById(R.id.headsupprep)
        showDataInRecView.layoutManager = LinearLayoutManager(this)
        getDataFromAPI()
        PostDataToAPI()
        search()


    }

    fun getDataFromAPI()
    {
        val getData = ClientCelebrity().featchData()?.create(CelebrityInterface::class.java)
        val getHeadsUp = getData?.getdata()
        if (getHeadsUp != null) {
            getHeadsUp.enqueue(object : Callback<ArrayList<CelebrityListData>?> {
                override fun onResponse(
                    call: Call<ArrayList<CelebrityListData>?>,
                    response: Response<ArrayList<CelebrityListData>?>
                ) {
                    if (response.code() == 200)
                    {
                        for(respon in response.body()!!) {
                            listOfPrep.add(respon)
                            showDataInRecView.adapter = RecViewHeadsUpPrep(listOfPrep)
                            showDataInRecView.adapter!!.notifyDataSetChanged()
                        }

                    }
                    else
                    {
                        Log.d("response code","${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<CelebrityListData>?>, t: Throwable) {
                    Log.d("response error","${t.message}")
                }
            })
        }
    }



    fun PostDataToAPI()
    {
        Binding.addHeads.setOnClickListener { postAlertDilog() }

    }

    fun postAlertDilog()
    {
        var postData = ClientCelebrity().featchData()?.create(CelebrityInterface::class.java)

        var newCelebrity = CelebrityListData("","","","")
        val buildDilog: AlertDialog.Builder = AlertDialog.Builder(this@HeadsUpPrep)
        val viewShow = layoutInflater.inflate(R.layout.add_to_headsup_prep, null)
        var nameText = viewShow.findViewById<TextInputEditText>(R.id.name_text).text
        var taboo1Text = viewShow.findViewById<TextInputEditText>(R.id.taboo1_text).text
        var taboo2Text = viewShow.findViewById<TextInputEditText>(R.id.taboo2_text).text
        var taboo3Text = viewShow.findViewById<TextInputEditText>(R.id.taboo3_text).text


        buildDilog.setPositiveButton("Add",DialogInterface.OnClickListener { dialog, which ->
            newCelebrity = CelebrityListData(nameText.toString(), taboo1Text.toString(),taboo2Text.toString(),taboo3Text.toString())
            var postHeadsUp = postData?.postdata(newCelebrity)

            if (postHeadsUp != null) {
                postHeadsUp.enqueue(object : Callback<ArrayList<CelebrityListData>?> {
                    override fun onResponse(
                        call: Call<ArrayList<CelebrityListData>?>,
                        response: Response<ArrayList<CelebrityListData>?>
                    ) {
                        if (response.code()==200)
                        {
                            Log.d("responsesu","${response.isSuccessful()}")
                            showDataInRecView.adapter!!.notifyDataSetChanged()

                        }

                        else
                        {
                            Log.d("response code","${response.code()}")

                        }
                    }

                    override fun onFailure(call: Call<ArrayList<CelebrityListData>?>, t: Throwable) {
                        Log.d("Post error","${t.message}")
                    }
                })
            }
        })
        buildDilog.setView(viewShow)
        buildDilog.create()
        buildDilog.show()

    }

    fun getDataWithPK(submitName: String)
    {

        Log.d("holdname","$submitName")
        var getData = ClientCelebrity().featchData()?.create(CelebrityInterface::class.java)
        var test = getData?.getdataWithPk2()
        if (test != null) {
            test.enqueue(object : Callback<ArrayList<ListDataWithPK>?> {
                override fun onResponse(
                    call: Call<ArrayList<ListDataWithPK>?>,
                    response: Response<ArrayList<ListDataWithPK>?>
                ) {
                    if (response.code()==200)
                    {
                        for (res in response.body()!!) {
                            listOfPrepWithPK.add(res)

                        }
                        //Log.d("456456","${listOfPrepWithPK}")

                    }
                }
                override fun onFailure(call: Call<ArrayList<ListDataWithPK>?>, t: Throwable) {
                    Log.d("APIPK", "${t.message}")
                }
            })
        }

        for(tes in listOfPrepWithPK )
            if (submitName == tes.name)
            {
                 holdPK = listOfPrepWithPK.indexOf(tes)
                var holdID = listOfPrepWithPK[holdPK].pk
                var holdNameInList = listOfPrepWithPK[holdPK].name


                getSpicificdata(holdID,holdNameInList,holdPK)
                //break

            }


    }

    fun getSpicificdata(PK: Int,name:String,holdHintPK:Int)
    {
        holdPK = holdHintPK
        var getData = ClientCelebrity().featchData()?.create(CelebrityInterface::class.java)

        var getHeadsUpWithPK = getData?.getspicifdata(PK,name)
        if (getHeadsUpWithPK != null) {
            getHeadsUpWithPK.enqueue(object : Callback<ArrayList<ListDataWithPK>>{
                override fun onResponse(
                    call: Call<ArrayList<ListDataWithPK>>,
                    response: Response<ArrayList<ListDataWithPK>>
                ) {
                    if (response.code()==200) {

                    }
                    else
                    {
                        Log.d("6547", "${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ListDataWithPK>>, t: Throwable) {
                    Log.d("APIPK", "${t.message}")
                }
            })
        }
        editDataInAPI(holdPK)


    }


    fun search()
    {
        var holdName = Binding.searchaboutname.text


        Binding.search.setOnClickListener {
            Log.d("holdname","${holdName.toString()}")
            println(holdName)

            getDataWithPK(holdName.toString())
        }
    }


    fun editDataInAPI(recivePK: Int)
    {
        Log.d("PK","$recivePK")
        var editData = ClientCelebrity().featchData()?.create(CelebrityInterface::class.java)

        var editCelebrity = CelebrityListData("","","","")
        val buildDilog: AlertDialog.Builder = AlertDialog.Builder(this@HeadsUpPrep)
        val viewShow = layoutInflater.inflate(R.layout.put_to_headsup_prep, null)
        var nameText = viewShow.findViewById<TextInputEditText>(R.id.name_put)
        var taboo1Text = viewShow.findViewById<TextInputEditText>(R.id.taboo1_put)
        var taboo2Text = viewShow.findViewById<TextInputEditText>(R.id.taboo2_put)
        var taboo3Text = viewShow.findViewById<TextInputEditText>(R.id.taboo3_put)


        var nameTextHint = viewShow.findViewById<TextInputLayout>(R.id.name_text_put)
        var taboo1TextHint = viewShow.findViewById<TextInputLayout>(R.id.taboo1_text_put)
        var taboo2TextHint = viewShow.findViewById<TextInputLayout>(R.id.taboo2_text_put)
        var taboo3TextHint = viewShow.findViewById<TextInputLayout>(R.id.taboo3_text_put)




        nameTextHint.hint = listOfPrepWithPK[recivePK].name
        taboo1TextHint.hint = listOfPrepWithPK[recivePK].taboo1
        taboo2TextHint.hint = listOfPrepWithPK[recivePK].taboo2
        taboo3TextHint.hint = listOfPrepWithPK[recivePK].taboo3


        var name = nameText.text
        var taboo1 = taboo1Text.text
        var taboo2 = taboo2Text.text
        var taboo3 = taboo3Text.text

        buildDilog.setPositiveButton("Edit",DialogInterface.OnClickListener { dialog, which ->

            editCelebrity = CelebrityListData(name.toString(), taboo1.toString(),taboo2.toString(),taboo3.toString())
            var sendEditData = editData?.updatedata(listOfPrepWithPK[recivePK].pk,editCelebrity)

            if (sendEditData != null) {
                sendEditData.enqueue(object : Callback<ArrayList<CelebrityListData>?> {
                    override fun onResponse(
                        call: Call<ArrayList<CelebrityListData>?>,
                        response: Response<ArrayList<CelebrityListData>?>
                    ) {
                        if (response.code()==200)
                        {
                            Log.d("Seuss","${response.isSuccessful}")
                            showDataInRecView.adapter!!.notifyDataSetChanged()
                        }
                        else{
                            Log.d("coderespo","${response.code()}")

                        }
                    }
                    override fun onFailure(call: Call<ArrayList<CelebrityListData>?>, t: Throwable) {
                        Log.d("Fail","${t.message}")
                    }
                })
            }


        })

        buildDilog.setNeutralButton("Delete",DialogInterface.OnClickListener { dialog, which ->

            var deleteData = editData?.deletedata(listOfPrepWithPK[recivePK].pk)

            if (deleteData != null) {
                deleteData.enqueue(object : Callback<Void?> {
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                        if (response.code()==200)
                        {
                            Log.d("Suess","${response.isSuccessful}")
                            showDataInRecView.adapter!!.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Log.d("FailDelete ","${t.message}")
                    }
                })
            }
        })

        buildDilog.setView(viewShow)
        buildDilog.create()
        buildDilog.show()
    }


}