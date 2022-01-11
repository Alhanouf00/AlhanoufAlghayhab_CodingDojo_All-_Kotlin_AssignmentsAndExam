package com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.RecView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.MainActivity
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.Models.RecipeDataItem
import com.example.alhanoufalghayhab_codingdojo_recipeappfirebase.databinding.RecipRecViewBinding

class RecipRecViewAdapter(val recipList: ArrayList<RecipeDataItem>, val activity: Context):
    RecyclerView.Adapter<RecipRecViewAdapter.RecipRecViewAdapterView>()  {


    inner class RecipRecViewAdapterView(val Binding: RecipRecViewBinding):RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipRecViewAdapterView {
        return RecipRecViewAdapterView(RecipRecViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecipRecViewAdapterView, position: Int) {


        //var PK = recipList[position].pk
        var Title = recipList[position]
        var Author = recipList[position]

        holder.Binding.apply {
            title.text = Title.title
            author.text = Author.author
            showRecipp.setOnClickListener(View.OnClickListener {
                var uuidValue = recipList[position].uuidValue
                var ing = recipList[position].ingredients
                var ins = recipList[position].instructions
                var title = recipList[position].title
                var author = recipList[position].author

                val intent= Intent(activity, MainActivity::class.java) //context we got from constructor
                intent.putExtra("ing",ing)
                intent.putExtra("ins",ins)
                intent.putExtra("uuid",uuidValue)
                intent.putExtra("title",title)
                intent.putExtra("author",author)
                activity.startActivity(intent)

            })

        }

    }

    override fun getItemCount(): Int {
        return recipList.size
    }

}
