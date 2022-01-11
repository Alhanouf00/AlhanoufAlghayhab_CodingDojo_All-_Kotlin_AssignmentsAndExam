package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.API.FlickrAPIInterface
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.API.FlickrClient
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.Models.PhotosData
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.RecView.FlickrRec
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var recViewPhoto: RecyclerView
    val listPohto = ArrayList<String>()
    val listTitle = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        recViewPhoto = findViewById(R.id.recyclerviewphoto)
        recViewPhoto.layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)

        search()

    }

    fun search() {
        Binding.search.setOnClickListener {
            var holdSearch = Binding.searchtext.text


            if (!holdSearch.isNullOrEmpty()) {
                CoroutineScope(IO).launch {

                    var recivephoto = async { getFlickrPhoto(holdSearch.toString()) }.await()

                    withContext(Main) {
                        listTitle.clear()
                        listPohto.clear()


                    }

                }
            }

        }


    }

    fun getFlickrPhoto(searchText: String) {
        var getPhoto = FlickrClient().featchPhoto()?.create(FlickrAPIInterface::class.java)

        var getPhotoUrl = getPhoto?.getPhotosList(searchText)
        if (getPhotoUrl != null) {
            getPhotoUrl.enqueue(object : Callback<PhotosData?> {
                override fun onResponse(
                    call: Call<PhotosData?>,
                    response: Response<PhotosData?>
                ) {
                    if (response.code() == 200) {
                        val holdPhotoUrl = response.body()?.photos?.photo

                        for (img in holdPhotoUrl!!) {

                            if (img.url_h != null) {

                                listPohto.add(img.url_h.toHttpUrl().encodedPath)
                                listTitle.add(img.title)
                                recViewPhoto.adapter = FlickrRec(listPohto, listTitle, this@MainActivity)
                                recViewPhoto.adapter!!.notifyDataSetChanged()
                            }


                        }
                    } else {
                        Log.d("photores", "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<PhotosData?>, t: Throwable) {
                    Log.d("errorMessage", "${t.message}")
                }
            })
        }

    }


}