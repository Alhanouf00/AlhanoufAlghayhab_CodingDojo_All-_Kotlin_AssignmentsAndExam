package com.example.favoritetvshows_finalexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritetvshows_finalexam.RecViews.RecTvShowDetails
import com.example.favoritetvshows_finalexam.Rooms.ShowTV
import com.example.favoritetvshows_finalexam.Rooms.TvShowDatabase
import com.example.favoritetvshows_finalexam.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity() {
    lateinit var Binding: ActivityDetailsBinding
    lateinit var recViewDetails: RecyclerView
    private val showDetails by lazy { TvShowDatabase.getDatabase(this@DetailsActivity).tvShowDao()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewDetails = findViewById(R.id.recdetails)
        recViewDetails.layoutManager = LinearLayoutManager(this@DetailsActivity)
        retrieveShowsTV()
    }

    fun retrieveShowsTV()
    {
        CoroutineScope(IO).launch {
            val showData = async {showDetails.getAllShows()}.await()
            if (showData !=null) {
                Log.d("456","$showData")

                withContext(Main) {

                    recViewDetails.adapter = RecTvShowDetails(showData,this@DetailsActivity)
                    recViewDetails.adapter!!.notifyDataSetChanged()
                }

            }
        }
    }

    fun deleteShowFromDatabase(id:Int,name:String,language:String,imageShow:String,summary:String)
    {
        CoroutineScope(IO).launch {

            showDetails.deleteTvShow(ShowTV(id, name, language,imageShow , summary))
        }
        retrieveShowsTV()
        Toast.makeText(this, "Delete successfully", Toast.LENGTH_LONG).show()

    }
}