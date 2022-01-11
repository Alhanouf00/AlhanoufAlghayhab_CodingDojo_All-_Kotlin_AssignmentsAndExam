package com.example.alhanoufalghayhab_codingdojo_3buttons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alhanoufalghayhab_codingdojo_3buttons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Binding:ActivityMainBinding
    var input1:String? = null
    var input2:String? = null
    var input3:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        threeButtons()
    }



    fun threeButtons()
    {

        Binding.displyintoast.setOnClickListener {

             input1 = Binding.input1.text.toString()
            input2 = Binding.input2.text.toString()
            input3 = Binding.input3.text.toString()
            Toast.makeText(this,"Edit Text 1 : $input1 \n Edit Text 2 : $input2 \n Edit Text 3 : $input3",
            Toast.LENGTH_LONG).show()

        }

        Binding.displayintext.setOnClickListener {
            input1 = Binding.input1.text.toString()
            input2 = Binding.input2.text.toString()
            input3 = Binding.input3.text.toString()

            Binding.textView.text = "Edit Text 1 : $input1 \n" +
                    " Edit Text 2 : $input2 \n" +
                    " Edit Text 3 : $input3"
        }

        Binding.AnotherActivity.setOnClickListener {
            input1 = Binding.input1.text.toString()
            input2 = Binding.input2.text.toString()
            input3 = Binding.input3.text.toString()

            intent = Intent(this,AnotherActivity::class.java)
            intent.putExtra("Edite1",input1)
            intent.putExtra("Edite2",input2)
            intent.putExtra("Edite3",input3)
            startActivity(intent)



        }






    }


}