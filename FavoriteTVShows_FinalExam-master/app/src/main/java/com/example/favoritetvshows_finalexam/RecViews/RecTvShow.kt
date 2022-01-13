package com.example.favoritetvshows_finalexam.RecViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritetvshows_finalexam.MainActivity
import com.example.favoritetvshows_finalexam.Models.TVShowItem
import com.example.favoritetvshows_finalexam.databinding.RecViewShowBinding
import java.util.ArrayList

class RecTvShow(val listName: ArrayList<TVShowItem>, val activity: MainActivity): RecyclerView.Adapter<RecTvShow.RecTvShowView>() {

    inner class RecTvShowView(val Bindings: RecViewShowBinding): RecyclerView.ViewHolder(Bindings.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTvShowView {
        return RecTvShowView(RecViewShowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun onBindViewHolder(holder: RecTvShowView, position: Int) {
        holder.Bindings.apply {
            val name = listName[position].show.name
            val language = listName[position].show.language
            val imageShow = listName[position].show.imageShow?.original
            val summary = listName[position].show?.summary
            nameofshow.text = listName[position].show.name


            nameofshow.setOnClickListener {
                if (imageShow != null) {
                    if (summary != null) {
                        activity.addShowToDatabase(name,language,imageShow,summary)
                    }
                }

                else{
                    activity.addShowToDatabase(name,language,"","No Summary")
                }
            }

        }
    }
    override fun getItemCount(): Int {
        return listName.size
    }
}