package com.example.alhanoufalghayhab_codingdojo_rssfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_rssfeed.Model.FeedData
import com.example.alhanoufalghayhab_codingdojo_rssfeed.databinding.RecViewBinding

class RecDataFeed(val listFeed:ArrayList<FeedData>):RecyclerView.Adapter<RecDataFeed.RecDataFeedView>() {
    inner class RecDataFeedView(val Binding: RecViewBinding): RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecDataFeedView {
        return RecDataFeedView(RecViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: RecDataFeedView, position: Int) {
        holder.Binding.apply {
            rank.text = listFeed[position].rank.toString()
            titlefeed.text = listFeed[position].title

        }
    }


    override fun getItemCount(): Int {
        return listFeed.size
    }
}