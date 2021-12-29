package com.example.alhanoufalghyhab_codingdojo_exam_mathstudyapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var saveData : SharedPreferences
    lateinit var inputSolution: TextInputEditText
    lateinit var highScoreText: TextView
    lateinit var scoreText: TextView
    lateinit var display: TextView
    //var holdText
    lateinit var submit: Button
    var counter = 0
    var score = 0
    var highScore = 0

    val equationDisplay= ArrayList<String>()

    private lateinit var rvMath: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        welcomAlert("Welcom to math study app \n \n This app will be generate random equations \n \n Train your brain" +
                "and try to solve it")
        rvMath= findViewById(R.id.recyclerView)


        saveData = getSharedPreferences("Score" , MODE_PRIVATE)

        rvMath.layoutManager = LinearLayoutManager(this)

        submitAnswer()

    }
    
    fun generateEquation():Int
    {
         display = findViewById<TextView>(R.id.displ)
        var randomNumber1 = 0
        var randomNumber2 = 0


        while (counter < 4) {

             randomNumber1 = (1..10).random()
             randomNumber2 = (1..10).random()
             display.text = randomNumber1.toString() + "+" + randomNumber2.toString() + "="
             return randomNumber1 + randomNumber2
        }

        while (counter < 8) {
            randomNumber1 = (11..50).random()
            randomNumber2 = (11..50).random()
            display.text = randomNumber1.toString() + "+" + randomNumber2.toString() + "="
            return randomNumber1 + randomNumber2

        }

            while (counter < 12) {
                randomNumber1 = (51..100).random()
                randomNumber2 = (51..100).random()
                display.text = randomNumber1.toString() + "+" + randomNumber2.toString() + "="
                return randomNumber1 + randomNumber2

            }


        while (counter < 16) {

            randomNumber1 = (100..200).random()
            randomNumber2 = (100..200).random()
            display.text = randomNumber1.toString() + "+" + randomNumber2.toString() + "="
            return randomNumber1 + randomNumber2
        }

            while (counter <= 20) {

                randomNumber1 = (300..1500).random()
                randomNumber2 = (300..1500).random()
                display.text = randomNumber1.toString() + "+" + randomNumber2.toString() + "="
                return randomNumber1 + randomNumber2
            }

                return 0
            }

            fun submitAnswer() {
                highScore = saveData.getInt("highScore", score)
                scoreText = findViewById(R.id.score)
                highScoreText = findViewById(R.id.highscore)
                highScoreText.text = "High score: " + highScore.toString()
                scoreText.text = "Score: "

                var holdIntegerResult = generateEquation()
                submit = findViewById<Button>(R.id.solv)
                inputSolution = findViewById(R.id.inputeq)

                var holdEqu = ""

                submit.setOnClickListener {
                    holdEqu = inputSolution.text.toString()
                    checkAnswer(holdIntegerResult, holdEqu.toInt())
                    equationDisplay.add("${display.text.toString()}"+holdEqu)
                    rvMath.adapter = MathRecView(equationDisplay)

                    rvMath.adapter!!.notifyDataSetChanged()
                    inputSolution.text = null
                    holdIntegerResult = generateEquation()
                    println("NumbersTotal $holdIntegerResult")


                }
            }

            fun checkAnswer(num1: Int, num2: Int) {


                if (score >19) {
                    endRoundAlert("You Are greate \n\n The game is end")
                    submit.isEnabled = false

                }

                var safeScore = saveData.edit()
                if (num1 == num2) {
                    println("Gooooooooooooooooood")
                    counter++
                    score++
                    scoreText.text = "Score: " + score.toString()
                    safeScore.putInt("highScore", score)
                    safeScore.apply()

                } else {
                    endRoundAlert("You are lose \n\n Do you want play again")
                    submit.isEnabled = false

                }

            }

            fun welcomAlert(message: String) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Welcome!")
                alertDialogBuilder.setMessage(message)
                alertDialogBuilder.setPositiveButton("I am ready ") { dialog, id -> null }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

            }

            fun endRoundAlert(message: String) {
                val alertDialogBuilder = AlertDialog.Builder(this)

                alertDialogBuilder.setTitle("Play agin!")
                alertDialogBuilder.setMessage(message)

                alertDialogBuilder.setPositiveButton(" Yes ") { dialog, id -> recreate() }
                alertDialogBuilder.setNegativeButton("No") { dialog, id -> null }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

        }


