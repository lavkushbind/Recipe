package com.example.newrecipe.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipe.Activity.SingleRecipeActivity
import com.example.newrecipe.Model.Details
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R

class Like_adapter(
    private val context: Context,
    private val postList: List<Details>
) : RecyclerView.Adapter<Like_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.categriesitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.titleTextView.text = post.title

//        holder.desc.text = post.desc
//        holder.cat.text = post.category
        Glide.with(context)
            .load(post.imageUrl)
            .placeholder(R.drawable.logo) // Placeholder image while loading
            .error(R.drawable.logo) // Image to show in case of loading error
            .into(holder.imageView)



        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleRecipeActivity::class.java)
            intent.putExtra("itemData", Details(post.postId, post.title, post.imageUrl, post.desc, post.likes, post.likeCount, post.category))

//            intent.putExtra("itemData", post) // Pass the item data to the new activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_catgries)
        val titleTextView: TextView = itemView.findViewById(R.id.text_catgries)
//        val desc: TextView = itemView.findViewById(R.id.textView10)
//        val cat: TextView = itemView.findViewById(R.id.textView11)

//        val postByTextView: TextView = itemView.findViewById(R.id.post)
        // Initialize other views
    }
}


