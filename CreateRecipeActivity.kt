package com.example.newrecipe.Activity
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.newrecipe.Model.Details
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class CreateRecipeActivity : Activity() {

    private lateinit var imageView: ImageView
    private lateinit var editTextText: EditText
    private lateinit var editTextText2: EditText
    private lateinit var editTextText3: EditText


    private lateinit var button: Button

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference: StorageReference = storage.reference
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        imageView = findViewById(R.id.imageView)
        editTextText2 = findViewById(R.id.desc)
        editTextText = findViewById(R.id.Rname)
        editTextText3 = findViewById(R.id.Rcat)
//
        button = findViewById(R.id.button3)

        imageView.setOnClickListener {
            chooseImage()
        }

        button.setOnClickListener {
            val title = editTextText.text.toString()
            val desc = editTextText2.text.toString()
            val category = editTextText3.text.toString()

            if (filePath != null) {
                uploadImageAndData(title,desc, category)

            }
            else {

                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            imageView.setImageURI(filePath)
        }
    }

    private fun uploadImageAndData(title: String, desc: String, category: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val recipeId = UUID.randomUUID().toString()
            val storageReference = storageReference.child("racipe_img")

            storageReference.putFile(filePath!!)
                .addOnSuccessListener { _ ->
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        val recipe = Details(recipeId, title, imageUrl, desc, 0, 0, category)

//                        val recipe = Details(recipeId, title, imageUrl,) // Pass recipeId and title
                        databaseReference.child("recipes").child(recipeId).setValue(recipe)
                        Toast.makeText(this, "Recipe uploaded successfully", Toast.LENGTH_SHORT).show()
                        val Intent = Intent(this, Home_Activity::class.java)
                        startActivity(Intent)
                        finish()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Image upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
