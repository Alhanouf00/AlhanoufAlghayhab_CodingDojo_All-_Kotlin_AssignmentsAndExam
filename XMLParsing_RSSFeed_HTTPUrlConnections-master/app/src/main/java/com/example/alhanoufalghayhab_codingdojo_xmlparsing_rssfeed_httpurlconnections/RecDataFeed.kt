package com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections.Model.DataRss
import com.example.alhanoufalghayhab_codingdojo_xmlparsing_rssfeed_httpurlconnections.databinding.RecViewDataBinding

import java.lang.Exception

class RecDataFeed(val listOfGame: ArrayList<DataRss>) :
    RecyclerView.Adapter<RecDataFeed.RecDataFeedView>() {
    inner class RecDataFeedView(val Binding: RecViewDataBinding) :
        RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecDataFeedView {
        return RecDataFeedView(
            RecViewDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecDataFeedView, position: Int) {
        val gameimg = "https://news.xbox.com${listOfGame[position].img}"
        holder.Binding.apply {

            Log.d("Arraygame", "${gameimg}")
            titlerss.text = listOfGame[position].titleGame


            Picasso.get().load(gameimg).into(imagegame)


        }
    }


    override fun getItemCount(): Int {
        return listOfGame.size
    }
}