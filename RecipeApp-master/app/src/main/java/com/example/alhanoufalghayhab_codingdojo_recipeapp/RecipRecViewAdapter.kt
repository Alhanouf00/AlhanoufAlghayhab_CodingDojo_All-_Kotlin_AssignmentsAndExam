package com.example.alhanoufalghayhab_codingdojo_recipeapp


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_recipeapp.databinding.RecipRecViewBinding

class RecipRecViewAdapter(val recipList: ArrayList<RecipeAPIDataItem>,val activity: Context):RecyclerView.Adapter<RecipRecViewAdapter.RecipRecViewAdapterView>()  {


    inner class RecipRecViewAdapterView(val Binding:RecipRecViewBinding):RecyclerView.ViewHolder(Binding.root)


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

                var ing = recipList[position].ingredients
                var ins = recipList[position].instructions

                val intent=    Intent(activity, MainActivity::class.java) //context we got from constructor
                intent.putExtra("ing",ing)
                intent.putExtra("ins",ins)
                activity.startActivity(intent)


                Log.d("ingredients","$ing")
                Log.d("instructions","$ins")


            })
        }
    }

    override fun getItemCount(): Int {
        return recipList.size
    }

}