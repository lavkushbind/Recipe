package com.example.newrecipe.Activity
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newrecipe.Model.Details
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class SingleRecipeActivity : Activity() {

    private lateinit var postId: String
    private lateinit var firebaseAuth: FirebaseAuth
//    private lateinit var currentUser: String

    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_recipe)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        databaseReference = FirebaseDatabase.getInstance().reference

      val detailImageView = findViewById<ImageView>(R.id.imageView)
      val save = findViewById<Button>(R.id.button3)

      save.setOnClickListener{
          savePost()
//          databaseReference.child(currentUser).child("Save").child(postId)


      }

      val titl = findViewById<TextView>(R.id.Stitl)
      val desc = findViewById<TextView>(R.id.desc)
      val cat = findViewById<TextView>(R.id.Scat)

      val item = intent.getParcelableExtra<Recipe_model>("itemData")

      if (item != null) {

          desc.text = item.desc
          titl.text = item.title
          cat.text = item.category
          postId=item.postId

          Glide.with(this)
              .load(item.imageUrl)
//              .placeholder(R.drawable.placeholder_image)
//              .error(R.drawable.error_image)
              .into(detailImageView)
      } else {
      }
  }

    private fun savePost() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid

            // Save the post information under the current user's node
            // Change "yourData" to the appropriate data you want to save
            databaseReference.child("Users").child(userId).child("Save").child(postId).setValue(postId)
            Toast.makeText(this,"Recipe Successfully saved", Toast.LENGTH_SHORT).show()
        }


}
}


