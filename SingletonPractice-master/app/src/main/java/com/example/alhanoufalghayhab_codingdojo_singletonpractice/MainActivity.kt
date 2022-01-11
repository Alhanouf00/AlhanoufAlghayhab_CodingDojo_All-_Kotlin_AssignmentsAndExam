package com.example.alhanoufalghayhab_codingdojo_singletonpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.alhanoufalghayhab_codingdojo_singletonpractice.API.APIInterface
import com.example.alhanoufalghayhab_codingdojo_singletonpractice.Model.APIData
import com.example.alhanoufalghayhab_codingdojo_singletonpractice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var selecItem = 0


    val listOfCurr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCurrencyAndConvert()
    }

    fun addCurrencyAndConvert() {
        featchData()
        binding.spinner.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, HoldConstValue.listNameCurr)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selecItem = position
            }

        }


        binding.conv.setOnClickListener {
            var value = binding.amount.text.toString().toDouble()

            binding.date.text = HoldConstValue.dateof
            calc(value, selecItem)
        }

    }


    fun calc(Value: Double, select: Int) {
        var valueOfAmount = 0.0
        var resultOfAmount = 0.0

        valueOfAmount = listOfCurr[select].toDouble()

        resultOfAmount = Value * valueOfAmount

        binding.result.text = resultOfAmount.toString()
    }

    fun featchData() {


        var retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/")
            .build()
            .create(APIInterface::class.java)


        var rerofitGetData = retrofitbuilder.getCurrency()

        rerofitGetData?.enqueue(object : Callback<APIData?> {
            override fun onResponse(call: Call<APIData?>, response: Response<APIData?>) {
                var responseBody = response.body()


                if (response.code() == 200) {

                    HoldConstValue.dateof = responseBody?.date.toString()

                    Log.d("Curr", "${response.body()?.eur}")
                    listOfCurr.add(responseBody?.eur?.ada.toString())
                    listOfCurr.add(responseBody?.eur?.aed.toString())
                    listOfCurr.add(responseBody?.eur?.afn.toString())
                    listOfCurr.add(responseBody?.eur?.all.toString())
                    listOfCurr.add(responseBody?.eur?.amd.toString())

                }
                Log.d("Teeg", "${listOfCurr}")


            }


            override fun onFailure(call: Call<APIData?>, t: Throwable) {
                Log.d("RetoError", "${t.message}")
            }
        })

    }
}