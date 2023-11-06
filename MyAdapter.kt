package com.example.newrecipe.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R

class MyAdapter(
                private  val List : ArrayList<Recipe_model>
): RecyclerView.Adapter<MyAdapter.MyViewHolder> (){






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categries,parent,false)
        return  MyViewHolder(itemView)



    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

   val currentitem =List[position]
        holder.textViewTitle.text=currentitem.title



    }
    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView){
val textViewTitle :TextView = itemView.findViewById(R.id.textViewTitle)
        val image : ImageView = itemView.findViewById(R.id.imageViewPost)

    }}