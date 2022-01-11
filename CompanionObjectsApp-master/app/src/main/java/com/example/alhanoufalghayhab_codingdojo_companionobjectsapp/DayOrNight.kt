package com.example.alhanoufalghayhab_codingdojo_companionobjectsapp

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import java.io.InputStream

class DayOrNight() {
    companion object
    {

        fun changeBackground(view: View, input:String,buttonback:Button,context:Context)
        {

            if (input=="Day") {

                val Day = ResourcesCompat.getDrawable(context.resources,R.drawable.day,null)
                view.background = Day

            }
                else if(input =="Night")
            {
                val Night:Drawable? = ResourcesCompat.getDrawable(context.resources,R.drawable.night,null)

                view.background = Night


            }

        }


    }

}