package com.example.alhanoufalghayhab_codingdojo_xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_xmlformat.databinding.ActivityMainBinding
import com.example.alhanoufalghayhab_codingdojo_xmlformat.model.XMLData
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding

    val studentsGradeList = ArrayList<XMLData>()
    var studentName=""
    var studentGrade=0.0
    var text = ""
    lateinit var RecView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        RecView = findViewById(R.id.recviewstudent)
        RecView.layoutManager = LinearLayoutManager(this)
        featchDataFromXML()
    }


    fun featchDataFromXML()
    {
        try {
            val openStudenList = assets.open("student_details.xml")
            val parsingFactory = XmlPullParserFactory.newInstance()
            val parsing = parsingFactory.newPullParser()
            parsing.setInput(openStudenList,null)
            var eventType = parsing.eventType
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                val tagName = parsing.name
                when(eventType)
                {
                    XmlPullParser.TEXT -> {text = parsing.text}
                    XmlPullParser.END_TAG -> when(tagName){
                        "name" ->{
                            studentName = text.toString()

                            //Log.d("stu","$text")
                        }
                        "grade" ->{ studentGrade = text.toDouble()
                            //Log.d("stu","$text")
                        }

                       // tagName.equals("name", ignoreCase = true)->{ studentName = text.toString()}
                        //tagName.equals("grade", ignoreCase = true)->{ studentGrade = text.toDouble()}
                        else ->{

                            Log.d("stu","$studentName")
                            Log.d("stu","$studentGrade")

                            if(!studentName.isEmpty()&&studentGrade!=0.0) {
                                val studen = XMLData(studentName, studentGrade)

                                studentsGradeList.add(studen)
                            }
                            studentName=""
                            studentGrade=0.0

                            //Log.d("stu","$studen")
                            //RecView.adapter = RecStudent(studentsGradeList)
                            //RecView.adapter!!.notifyDataSetChanged()
                        }
                    }
                    else->{
                        Log.d("Nothing","Nothing")}

                }

                eventType = parsing.next()
            }
        }catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        RecView.adapter = RecStudent(studentsGradeList)
        RecView.adapter!!.notifyDataSetChanged()


    }

}