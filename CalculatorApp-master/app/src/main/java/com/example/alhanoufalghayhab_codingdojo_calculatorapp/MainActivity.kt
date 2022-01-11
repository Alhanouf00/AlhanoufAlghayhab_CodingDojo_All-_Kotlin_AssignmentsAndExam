package com.example.alhanoufalghayhab_codingdojo_calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {
    var calcOnScreen = StringBuilder()
    var resultOnScreen = StringBuilder()
    var resultofcalc:Double = 0.0
    var number1:Double = 0.0
    var holdoper = ""
    var number2:Double = 0.0
    lateinit var textV :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numricButtons()
        opration()
        reset()
    }

fun stuffOnscreen(reciveButtons:String)
{
     textV = findViewById(R.id.textView)
    calcOnScreen.append(reciveButtons)
    textV.text = calcOnScreen.toString()

}

    fun numricButtons()
    {
        var nine = findViewById<Button>(R.id.nine)


        nine.setOnClickListener { stuffOnscreen("9")  }

        var eight = findViewById<Button>(R.id.eight)

        eight.setOnClickListener {stuffOnscreen("8")  }

        var seven = findViewById<Button>(R.id.seven)

        seven.setOnClickListener { stuffOnscreen("7") }

        var six = findViewById<Button>(R.id.six)

        six.setOnClickListener { stuffOnscreen("6") }

        var five = findViewById<Button>(R.id.five)

        five.setOnClickListener { stuffOnscreen("5") }

        var four = findViewById<Button>(R.id.four)

        four.setOnClickListener { stuffOnscreen("4") }

        var three = findViewById<Button>(R.id.three)

        three.setOnClickListener { stuffOnscreen("3") }

        var two = findViewById<Button>(R.id.two)

        two.setOnClickListener { stuffOnscreen("2") }

        var one = findViewById<Button>(R.id.one)

        one.setOnClickListener {stuffOnscreen("1")  }

        var zero = findViewById<Button>(R.id.zero)

        zero.setOnClickListener { stuffOnscreen("0") }
    }

    fun opration()

    {


        var add = findViewById<Button>(R.id.add)

        add.setOnClickListener {opration("+")}

        var minus = findViewById<Button>(R.id.minus)

        minus.setOnClickListener { opration("-") }

        var multi = findViewById<Button>(R.id.multi)

        multi.setOnClickListener { opration("*") }

        var divide = findViewById<Button>(R.id.divide)

        divide.setOnClickListener { opration("/") }

        var equal = findViewById<Button>(R.id.equal)
        equal.setOnClickListener { calc() }
    }

    fun reset()
    {

        var del = findViewById<Button>(R.id.del)

        del.setOnClickListener {
            number1 = 0.0
            number2 = 0.0

            textV.text = "0"
        }

        var clear = findViewById<Button>(R.id.clear)

        clear.setOnClickListener {
            textV.text = "0"
            calcOnScreen.clear()
            resultOnScreen.clear()
            number1
            number2 = 0.0
        }

    }

    fun opration(opar:String) {

        number1 = calcOnScreen.toString().toDouble()
        calcOnScreen.clear()

        when (opar) {
            "+" -> {holdoper = "+"}
            "-" -> {holdoper = "-"}
            "*" -> {holdoper = "*"}
            "/" -> {holdoper = "/"}
        }
    }



    fun calc()
    {
        number2 = calcOnScreen.toString().toDouble()
        calcOnScreen.clear()

        when (holdoper) {

            "+" -> {resultofcalc = number1+number2

                resultOnScreen.append(resultofcalc)
                textV.text = resultOnScreen.toString()
                resultOnScreen.clear()
            }
            "-" -> {resultofcalc = number1-number2
                resultOnScreen.append(resultofcalc)
                textV.text = resultOnScreen.toString()
                resultOnScreen.clear()
            }
            "*" -> {resultofcalc = number1*number2
                resultOnScreen.append(resultofcalc)
                textV.text = resultOnScreen.toString()
                resultOnScreen.clear()
            }
            "/" -> {resultofcalc = number1/number2

                if (number2.toInt() == 0)
                {
                    Toast.makeText(this@MainActivity,"Can not be divide on zero",Toast.LENGTH_LONG)
                }

                resultOnScreen.append(resultofcalc)
                textV.text = resultOnScreen.toString()
                resultOnScreen.clear()
            }

        }


    }
}