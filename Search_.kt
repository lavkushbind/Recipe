package com.example.newrecipe.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipe.Activity.SingleRecipeActivity
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R

import com.squareup.picasso.Picasso

class Search_(private val postList: List<Recipe_model>, private val context: Context) :
    RecyclerView.Adapter<Search_.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Recipe_model = postList[position]
        holder.name.text = Recipe_model.title

        Picasso.get()
            .load(Recipe_model.imageUrl)
            .into(holder.titleImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleRecipeActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.imageViewPost)
        val name: TextView = itemView.findViewById(R.id.textViewTitle)
    }
}
