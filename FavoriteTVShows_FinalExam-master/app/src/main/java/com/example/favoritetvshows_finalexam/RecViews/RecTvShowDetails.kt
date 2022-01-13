package com.example.favoritetvshows_finalexam.RecViews

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritetvshows_finalexam.DetailsActivity
import com.example.favoritetvshows_finalexam.R
import com.example.favoritetvshows_finalexam.Rooms.ShowTV
import com.example.favoritetvshows_finalexam.databinding.RecViewShowDetailsBinding
import com.squareup.picasso.Picasso

class RecTvShowDetails(val listDetails:List<ShowTV>, val activity: DetailsActivity): RecyclerView.Adapter<RecTvShowDetails.RecTvShowDetailsView>() {
    inner class RecTvShowDetailsView(val Bindings: RecViewShowDetailsBinding): RecyclerView.ViewHolder(Bindings.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecTvShowDetailsView {
        return RecTvShowDetailsView(
            RecViewShowDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }
    override fun onBindViewHolder(holder: RecTvShowDetailsView, position: Int) {
        holder.Bindings.apply {
            val name = listDetails[position].name
            val language = listDetails[position].language
            val imageShow = listDetails[position].imageshow
            val summary = listDetails[position].summary
            val id = listDetails[position].id

            textname.text = "Title: "+name
            textlang.text = "Language: "+ language


            if(imageShow.isNullOrEmpty())
            {
                imageshow.setImageResource(R.drawable.noimage)
            }

            else {

                Picasso.get().load(imageShow).resize(150, 200).into(imageshow)
            }

            cardview.setOnClickListener {

                activity.deleteShowFromDatabase(id,name,language,imageShow,summary)

            }

            expanddown.setOnClickListener {

                if (expandableLayout.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardview, AutoTransition())
                    expandableLayout.visibility = View.VISIBLE
                    descriptionTV.text = summary
                    expanddown.setImageResource(R.drawable.arrow_down_24)

                } else {
                    TransitionManager.beginDelayedTransition(cardview, AutoTransition())
                    expandableLayout.visibility = View.GONE
                    expanddown.setImageResource(R.drawable.arrow_up_24)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listDetails.size
    }
}