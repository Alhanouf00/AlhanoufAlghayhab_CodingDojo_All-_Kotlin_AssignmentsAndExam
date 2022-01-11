package com.example.alhanoufalghayhab_codingdojo_topapps.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_topapps.Model.TopAppData
import com.example.alhanoufalghayhab_codingdojo_topapps.R
import com.example.alhanoufalghayhab_codingdojo_topapps.RecView.RecTop10
import com.example.alhanoufalghayhab_codingdojo_topapps.databinding.ActivityTop10Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class Top10Activity : AppCompatActivity() {
    lateinit var  Binding:ActivityTop10Binding
    lateinit var recView10:RecyclerView
    val topTenList = ArrayList<TopAppData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityTop10Binding.inflate(layoutInflater)
        setContentView(Binding.root)
        recView10 = findViewById(R.id.rec10viewapp)
        recView10.layoutManager = LinearLayoutManager(this)
        getFeedData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topapp , menu)

        return true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.top100app -> {
                val inten100 = Intent(this@Top10Activity,Top100Activity::class.java)
                startActivity(inten100)

            }
            R.id.top10app -> {
                Toast.makeText(this@Top10Activity, "Android Menu is Clicked", Toast.LENGTH_LONG).show()

            }

        }
        return super.onOptionsItemSelected(item)
    }



    fun getFeedData()
    {
        Binding.getfeed.setOnClickListener {
        CoroutineScope(IO).launch {

            var dataFeed = async {fetchFeedData()}.await()

            withContext(Main)
            {
                recView10.adapter = RecTop10(topTenList)
                recView10.adapter!!.notifyDataSetChanged()

            }

        }}
    }

    fun fetchFeedData() {
        var Top10 = ""
        var text = ""
        try {
            val parsingFactory = XmlPullParserFactory.newInstance()
            val parsing = parsingFactory.newPullParser()
            val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            parsing.setInput(url.openStream(), null)
            var eventType = parsing.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parsing.name
                when (eventType) {
                    XmlPullParser.TEXT -> {
                        text = parsing.text
                    }
                    XmlPullParser.END_TAG -> when (tagName) {
                        "im:name" -> {
                            Top10 = text
                        }

                        else -> {
                            Log.d("App10","$Top10")

                            if(!Top10.isEmpty()) {
                                val App = TopAppData(Top10)
                                topTenList.add(App)
                            }
                            Top10 = ""
                        }
                    }
                    else -> {

                    }
                }
                eventType = parsing.next()
            }
        }
        catch(e: XmlPullParserException) {
            e.printStackTrace()
        }
        catch(e: IOException) {
            e.printStackTrace()
        }

    }

}