package com.example.alhanoufalghayhab_codingdojo_weatherapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.alhanoufalghayhab_codingdojo_weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var zipCode = "10001"
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWeatherData()
        refreshData()
        changeCity()


    }



    fun getWeatherData() {
        CoroutineScope(IO).launch {

            Log.d("zipcode in get","$zipCode")
            var weatherData = async { featchData(zipCode) }.await()

            if (!weatherData.isEmpty()) {

                var jsonOfWeather = JSONObject(weatherData)
                var holdHumadity = jsonOfWeather.getJSONObject("main").getString("humidity")
                var holdPressure = jsonOfWeather.getJSONObject("main").getString("pressure")
                var holdMinTemp = jsonOfWeather.getJSONObject("main").getString("temp_min")
                var holdMaxTemp = jsonOfWeather.getJSONObject("main").getString("temp_max")
                var holdTemp = jsonOfWeather.getJSONObject("main").getString("temp")
                var holdWind = jsonOfWeather.getJSONObject("wind").getString("speed")
                var holdCityname = jsonOfWeather.getString("name")
                var holdCountry = jsonOfWeather.getJSONObject("sys").getString("country")
                var holdDate = jsonOfWeather.getString("timezone")
                var holdSunrise = jsonOfWeather.getJSONObject("sys").getString("sunrise")
                var holdSunset = jsonOfWeather.getJSONObject("sys").getString("sunset")






                withContext(Main){
                    binding.humadity.text = holdHumadity
                    binding.prss.text =holdPressure
                    binding.rate.text = holdWind
                    binding.cityName.text = holdCityname + ","+holdCountry
                    converZone(holdSunrise,holdSunset,holdDate)
                    convertUnit(holdMinTemp.toDouble(),holdMaxTemp.toDouble(),holdTemp.toDouble())


                }




            }
        }
    }

    fun featchData(zipCodeRecive:String):String
    {
        Log.d("zipcode in fetch","$zipCode")
        var weatherResponce = ""

        try {
            weatherResponce = URL("https://api.openweathermap.org/data/2.5/weather?zip=$zipCodeRecive,us&APPID=e64df36e188b5f092195fbd94210ee88").readText()
        }catch (e: Exception)
        {
            Log.d("advice","$e")

        }

        return weatherResponce
    }


    fun converZone(sunrise:String,sunset:String,dateCity:String)
    {



        Log.d("Datecity","${ SimpleDateFormat("dd/mm/yyyy hh:mm ", Locale.ENGLISH).format(Date(dateCity.toLong()*1000))}")

       binding.timesurise.text = SimpleDateFormat("HH:MM").format(Date(sunrise.toLong()*1000))
       binding.timeSunset.text = SimpleDateFormat("HH:MM").format(Date(sunset.toLong()*1000))
       binding.updateDate.text = SimpleDateFormat("dd/mm/yyyy HH:MM").format(Date(dateCity.toLong()*1000))


    }


    fun convertUnit(low:Double,high:Double,temp:Double)
    {
        Log.d("Low","$low")
        var lowCel = low-273.15
        var highCel= high-273.15
        var tempCel= temp-273.15
        binding.lowtemp.text = "Low:  "+lowCel.toFloat()+"° C"
        binding.hightemp.text = "High:  "+highCel.toFloat()+"° C"
        binding.temp.text =""+tempCel.toFloat()+"° C"

    }

    fun refreshData() {
        binding.refreshimg.setOnClickListener {

            getWeatherData()


            Log.d("click", "Is click")
        }
    }

    fun changeCity()
    {
        binding.change.setOnClickListener {  showdialog()}

    }


        fun showdialog(){
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Zip code")


            val input = EditText(this)
            input.setHint("Enter zip code")
            builder.setView(input)

            builder.setPositiveButton("Enter zip code", DialogInterface.OnClickListener { dialog, id ->
                var zipCodeText = input.text.toString()
                zipCode = zipCodeText
                getWeatherData()
                Log.d("zipcode in alert","$zipCode")



            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

            builder.create()
            builder.show()
        }



    }









