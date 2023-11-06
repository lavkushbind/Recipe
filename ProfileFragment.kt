package com.example.newrecipe.Fragment
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newrecipe.Activity.CreateRecipeActivity
import com.example.newrecipe.Activity.Signup
import com.example.newrecipe.Model.User
import com.example.newrecipe.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.util.UUID

class ProfileFragment : Fragment() {

    private lateinit var imageview4: ImageView
    private lateinit var button4: Button
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var userId: String = ""
    private lateinit var userName: TextView
    private lateinit var email_text: TextView
    private lateinit var phone_text: TextView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            userId = currentUser.uid

            val userEmail = currentUser.email
            val profileEmailTextView = binding.emailText
            profileEmailTextView.text = userEmail


            val userName = currentUser.displayName
            val userId = currentUser.uid

            val useridd = binding.phoneText

            val profileNameTextView = binding.UserName
            profileNameTextView.text = userName

            // Set user's name and email in the TextViews
            useridd.text = userId


        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        storageReference = FirebaseStorage.getInstance().getReference("profile_images")

        button4 = binding.button4
        button4.setOnClickListener {


            showLogoutConfirmationDialog()

//            val intent = Intent(requireContext(), CreateRecipeActivity::class.java)
//            startActivity(intent)
        }

        imageview4 = binding.imageView4




        imageview4.setOnClickListener {
            chooseImage()
        }

        loadProfileImage()

        return binding.root
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialog, which ->
            // Handle logout
            performLogout()
        }
        builder.setNegativeButton("No") { dialog, which ->
            // Dismiss the dialog
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun performLogout() {
        firebaseAuth.signOut()
        val intent = Intent(requireContext(), Signup::class.java)
        startActivity(intent)
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
            imageview4.setImageURI(filePath)
            uploadImage()
        }
    }

    private fun uploadImage() {
        val imageRef = storageReference.child("profilepic").child(userId).child("images/${UUID.randomUUID()}")
        filePath?.let {
            imageRef.putFile(it)
                .addOnSuccessListener { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageURL = uri.toString()

                        databaseReference.child(userId).child("profilePic").setValue(imageURL)
                        loadProfileImage()
                    }
                }
                .addOnFailureListener { e ->
                }
        }
    }

    private fun loadProfileImage() {
        databaseReference.child(userId).child("profilePic").get().addOnSuccessListener { dataSnapshot ->
            val imageUrl = dataSnapshot.getValue(String::class.java)
            if (!imageUrl.isNullOrEmpty()) {
                Picasso.get().load(imageUrl).into(imageview4)
            }
        }.addOnFailureListener {
        }
    }
}
