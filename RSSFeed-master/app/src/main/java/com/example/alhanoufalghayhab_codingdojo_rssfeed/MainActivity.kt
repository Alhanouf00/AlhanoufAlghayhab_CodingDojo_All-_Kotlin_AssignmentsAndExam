package com.example.alhanoufalghayhab_codingdojo_rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_rssfeed.Model.FeedData
import com.example.alhanoufalghayhab_codingdojo_rssfeed.databinding.ActivityMainBinding
import com.example.alhanoufalghayhab_codingdojo_rssfeed.databinding.RecViewBinding
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

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var recViewFeed: RecyclerView
    val FeedList = ArrayList<FeedData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewFeed = findViewById(R.id.recviewfeed)
        recViewFeed.layoutManager = LinearLayoutManager(this)
        getFeedData()

    }




    fun getFeedData()
    {
        CoroutineScope(IO).launch {

            var dataFeed = async {fetchFeedData()}.await()

            withContext(Main)
            {

                recViewFeed.adapter = RecDataFeed(FeedList)
                recViewFeed.adapter!!.notifyDataSetChanged()



            }

        }




    }


    fun fetchFeedData() {
        var tilteFeed = ""
        var rankFeed = 0
        var text = ""
        try {
            val parsingFactory = XmlPullParserFactory.newInstance()
            val parsing = parsingFactory.newPullParser()
            val url = URL("https://stackoverflow.com/feeds")
            parsing.setInput(url.openStream(), null)
            var eventType = parsing.eventType

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    val tagName = parsing.name
                    when (eventType) {
                        XmlPullParser.TEXT -> {
                            text = parsing.text
                        }
                        XmlPullParser.END_TAG -> when (tagName) {
                            "title" -> {
                                tilteFeed = text.toString()
                            }
                            "rank" -> {
                                rankFeed = text.toInt()

                            }
                            else -> {

                                if(!tilteFeed.isEmpty()) {
                                    val studen = FeedData(tilteFeed, rankFeed)
                                    FeedList.add(studen)
                                }
                                tilteFeed = ""


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