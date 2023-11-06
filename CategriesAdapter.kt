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
class CategriesAdapter(

    private val context: Context,
    private val postList: List<Details>
) : RecyclerView.Adapter<CategriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategriesAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_categries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val post = postList[position]
        holder.titleTextView.text = post.category
        Glide.with(context)
            .load(post.imageUrl)
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .into(holder.imageView)



        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleRecipeActivity::class.java)
            intent.putExtra("itemData", post) // Pass the item data to the new activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewPost)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
    }
}