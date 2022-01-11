package com.example.alhanoufalghayhab_codingdojo_parsinglocaljsonfile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_parsinglocaljsonfile.databinding.RecImageViewBinding
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl.Companion.toHttpUrl


class RecImage(val listOfimage: ArrayList<String>) : RecyclerView.Adapter<RecImage.RecImageView>() {
    inner class RecImageView(val Binding: RecImageViewBinding) :
        RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecImageView {
        return RecImageView(
            RecImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecImageView, position: Int) {
        holder.Binding.apply {
            Picasso.get().load(listOfimage[position]).resize(300, 300).into(imageviewdata)

        }
    }

    override fun getItemCount(): Int {
        return listOfimage.size
    }
}