package com.example.alhanoufalghayhab_codingdojo_postrequestpractice

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_postrequestpractice.databinding.RecViewApiBinding

class RecViewAdapter(var apiList: ArrayList<DataAPIItem>,var listPk: ArrayList<UpdateData>,var activity: Context):RecyclerView.Adapter<RecViewAdapter.RecViewAdapterView>() {
    inner class RecViewAdapterView (val Binding:RecViewApiBinding):RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewAdapterView {
        return RecViewAdapterView(RecViewApiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: RecViewAdapterView, position: Int) {
        var Name = apiList[position]
        var Location = apiList[position]





        holder.Binding.apply {
            name.text = Name.name
            location.text = Location.location
            showUser.setOnClickListener(View.OnClickListener {
                val intent= Intent(activity, MainActivity::class.java) //context we got from constructor
                intent.putExtra("name",apiList[position].name)
                intent.putExtra("location",apiList[position].location)
                intent.putExtra("PK",listPk[position].pk)
                activity.startActivity(intent)

                Log.d("userupdate",apiList[position].name)
                Log.d("userupdate",apiList[position].location)
                Log.d("userupdate","${listPk[position].pk}")


            })
        }

    }

    override fun getItemCount(): Int {
        return apiList.size
    }


}