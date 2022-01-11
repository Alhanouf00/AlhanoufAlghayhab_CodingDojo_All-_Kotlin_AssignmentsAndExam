package com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.RecView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.DetailActivity
import com.example.alhanoufalghayhab_codingdojo_flickrbrowserapp.databinding.FlickRecViewBinding
import com.squareup.picasso.Picasso

class FlickrRec(
    val listOfPhoto: ArrayList<String>,
    val listOfTitle: ArrayList<String>,
    val context: Context
) : RecyclerView.Adapter<FlickrRec.FlickrRecView>() {
    inner class FlickrRecView(val Binding: FlickRecViewBinding) :
        RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrRecView {
        return FlickrRecView(
            FlickRecViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FlickrRecView, position: Int) {

        val imgFlickr1 = "https://live.staticflickr.com${listOfPhoto[position]}"
        val imgtitle = listOfTitle[position]
        holder.Binding.apply {
            Picasso.get().load(imgFlickr1).resize(180, 150).into(imageview1)
            cardimage.setOnClickListener(View.OnClickListener {
                Log.d("IsClick", "ClisckClick")
                val sendDetailOfImage = Intent(context, DetailActivity::class.java)
                sendDetailOfImage.putExtra("imagedetail", imgFlickr1)
                sendDetailOfImage.putExtra("title", imgtitle)
                context.startActivity(sendDetailOfImage)

            })

        }
    }

    override fun getItemCount(): Int {
        return listOfPhoto.size
    }
}


