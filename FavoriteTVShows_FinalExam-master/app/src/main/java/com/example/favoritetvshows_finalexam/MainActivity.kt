package com.example.favoritetvshows_finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritetvshows_finalexam.API.APIClient
import com.example.favoritetvshows_finalexam.API.APIInterface
import com.example.favoritetvshows_finalexam.Models.TVShow
import com.example.favoritetvshows_finalexam.Models.TVShowItem
import com.example.favoritetvshows_finalexam.RecViews.RecTvShow
import com.example.favoritetvshows_finalexam.Rooms.ShowTV
import com.example.favoritetvshows_finalexam.Rooms.TvShowDatabase
import com.example.favoritetvshows_finalexam.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var recViewShow: RecyclerView
    val listOfShow = ArrayList<TVShowItem>()
    private val showDao by lazy { TvShowDatabase.getDatabase(this@MainActivity).tvShowDao()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewShow = findViewById(R.id.recyclerView)
        recViewShow.layoutManager = LinearLayoutManager(this@MainActivity)
        search()
        gotToDetails()
    }

    fun search() {
        Binding.searchbutton.setOnClickListener {
            var holdSearch = Binding.searchfield.text


            if (!holdSearch.isNullOrEmpty()) {
                CoroutineScope(IO).launch {

                    var reciveTvShow = async { getShowTvData(holdSearch.toString()) }.await()

                }
            }
            else {
                Toast.makeText(this@MainActivity, "The search not found", Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
    fun getShowTvData(searchText: String) {
        listOfShow.clear()

        var getPhoto = APIClient().featchData()?.create(APIInterface::class.java)

        var getPhotoUrl = getPhoto?.getPhotosList(searchText)
        if (getPhotoUrl != null) {
            getPhotoUrl.enqueue(object : Callback<TVShow?> {
                override fun onResponse(
                    call: Call<TVShow?>,
                    response: Response<TVShow?>
                ) {
                    if (response.code() == 200) {

                        val result = response.body()
                        Log.d("123","$result")
                        for (i in result!!) {
                            listOfShow.add(i)
                            recViewShow.adapter = RecTvShow(listOfShow, this@MainActivity)
                            recViewShow.adapter!!.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<TVShow?>, t: Throwable) {
                    Log.d("errorMessage", "${t.message}")
                }
            })
        }
    }
    fun addShowToDatabase(name:String,language:String,imageShow:String,summary:String)
    {

        CoroutineScope(IO).launch {

            showDao.addTvShow(ShowTV(0, name, language,imageShow , summary))
        }
        Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()

    }

    fun gotToDetails()
    {
        Binding.go.setOnClickListener {
            val sendDetails = Intent(this@MainActivity, DetailsActivity::class.java)
            startActivity(sendDetails)

        }

    }
}

