package com.example.alhanoufalghayhab_codingdojo_headsupgame.MainActivity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_headsupgame.API.CelebrityInterface
import com.example.alhanoufalghayhab_codingdojo_headsupgame.API.ClientCelebrity
import com.example.alhanoufalghayhab_codingdojo_headsupgame.Models.CelebrityListData
import com.example.alhanoufalghayhab_codingdojo_headsupgame.RecView.RecViewHeadsUpPrep
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.ActivityHeadsUpIntroBinding
import com.example.alhanoufalghayhab_codingdojo_headsupgame.databinding.ActivityHeadsUpPrepBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadsUpIntro : AppCompatActivity() {
    lateinit var Binding: ActivityHeadsUpIntroBinding
    val listOfIntro= ArrayList<CelebrityListData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityHeadsUpIntroBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        getDataFromAPI()
        startAGame()

    }

    fun startAGame() {

        val orientation = getResources().getConfiguration().orientation

        Binding.namecele.text = ""
        Binding.taboo1cele.text = ""
        Binding.taboo2cele.text = ""
        Binding.taboo3cele.text = ""
        Binding.start.setOnClickListener {

            if (orientation== Configuration.ORIENTATION_PORTRAIT) {
                Binding.namecele.text = "Rotate To begin a game"
            }

            else if (orientation== Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(this, "Landscape Mode", Toast.LENGTH_SHORT).show()

                if (!listOfIntro.isEmpty()) {
                    val holdRandom = (0..listOfIntro.size - 1).random()
                    Binding.namecele.text = listOfIntro[holdRandom].name
                    Binding.taboo1cele.text = listOfIntro[holdRandom].taboo1
                    Binding.taboo2cele.text = listOfIntro[holdRandom].taboo2
                    Binding.taboo3cele.text = listOfIntro[holdRandom].taboo3


                    object : CountDownTimer(60000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            Binding.start.isEnabled = false
                            Binding.timer.setText("seconds remaining: " + millisUntilFinished / 1000)
                        }

                        override fun onFinish() {
                            Binding.start.isEnabled = true
                            Binding.timer.setText("done!")
                        }
                    }.start()
                } else {
                    Toast.makeText(
                        this,
                        "Wait a data then press start game again",
                        Toast.LENGTH_LONG
                    )
                }
            }
    }

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
                            listOfIntro.add(respon)
                            Log.d("listOfIntro","${listOfIntro}")
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


}