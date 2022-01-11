package com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.core.text.htmlEncode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections.Model.DataRss
import com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var RecViewDataFeed: RecyclerView
    val listOfTitle = ArrayList<DataRss>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        RecViewDataFeed = findViewById(R.id.recviewgame)
        RecViewDataFeed.layoutManager =
            LinearLayoutManager(this)//I Forgot to add this thats why the data not appear in RecView
        getFeedData()
    }

    fun getFeedData() {

        CoroutineScope(IO).launch {

            var dataTitleGame = async { fetchFeedData() }.await()

            withContext(Main)
            {
                RecViewDataFeed.adapter = RecDataFeed(listOfTitle)
                RecViewDataFeed.adapter!!.notifyDataSetChanged()

            }

        }

    }

    fun fetchFeedData() {
        var tilteGame = ""
        var imgOfaGame = ""
        var text = ""
        try {
            val parsingFactory = XmlPullParserFactory.newInstance()
            val parsing = parsingFactory.newPullParser()
            val url = URL("https://news.xbox.com/en-us/feed/")
            parsing.setInput(url.openStream(), null)
            var eventType = parsing.eventType
            //Log.d("Gameevent","${eventType}")
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parsing.name
                when (eventType) {
                    XmlPullParser.TEXT -> {
                        text = parsing.text
                    }
                    XmlPullParser.END_TAG -> when (tagName) {
                        "title" -> {
                            tilteGame = text

                        }
                        "media:thumbnail" -> {
                            imgOfaGame = parsing.getAttributeValue(0).toHttpUrl().encodedPath

                        }

                        else -> {

                            if (!tilteGame.isEmpty() && !imgOfaGame.isEmpty()) {
                                Log.d("Game", "${imgOfaGame}")

                                val gameNews = DataRss(tilteGame, imgOfaGame)
                                listOfTitle.add(gameNews)
                            }
                            tilteGame = ""
                        }
                    }
                    else -> {

                    }
                }
                eventType = parsing.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}