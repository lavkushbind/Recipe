package com.example.newrecipe.Fragmentimport
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipe.Activity.CreateRecipeActivity
import com.example.newrecipe.Adapter.CategriesAdapter
import com.example.newrecipe.Adapter.PostAdapter
import com.example.newrecipe.Adapter.Search_
import com.example.newrecipe.Model.Details
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.Model.User
import com.example.newrecipe.R
import com.example.newrecipe.databinding.FragmentFavoriteBinding
import com.example.newrecipe.databinding.FragmentHomeBinding
import com.example.newrecipe.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var username: TextView
    private lateinit var profileview2: ImageView
    private lateinit var Create_img: ImageView
    private lateinit var Create_btn: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategriesAdapter
    private lateinit var adapter2: PostAdapter
    private val postList = ArrayList<Details>()
    private val postList2 = ArrayList<Details>()

//    private lateinit var firebaseAuth: FirebaseAuth


        private var _binding: FragmentHomeBinding? = null
    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        username=view.findViewById(R.id.username)
        profileview2=view.findViewById(R.id.profilepic2)

        Create_btn= view.findViewById(R.id.button90)
//        firebaseAuth = FirebaseAuth.getInstance()
//        val currentUser = firebaseAuth.currentUser

//        val currentUser = FirebaseAuth.getInstance().currentUser?.uid
//        if (currentUser != null) {
////            databaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(currentUser)
//            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//
////            databaseReference.addValueEventListener(object : ValueEventListener {
////                override fun onDataChange(snapshot: DataSnapshot) {
////                    if (snapshot.exists()) {
////                        val userProfile = snapshot.getValue(User::class.java)
////                        if (userProfile != null) {
////                            val name = userProfile.name
////
////                            val profilePicUrl = userProfile.profilePic
////
////                            username.text = name
////
////                            context?.let {
////                                Glide.with(it)
////                                    .load(profilePicUrl)
////                                    .into(profileview2)
////                            }
////                        }
////                    }
////                }
////
////                override fun onCancelled(error: DatabaseError) {
////                    // Handle the error
////                }
////            })
//        }


        Create_btn.setOnClickListener {
            val intent = Intent(requireContext(), CreateRecipeActivity::class.java)
            startActivity(intent)
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        val _binding = FragmentHomeBinding.inflate(inflater, container, false)



        val recyclerView2 = view.findViewById<RecyclerView>(R.id.RecipeID)
        val layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView2.layoutManager = layoutManager2
        adapter2 = PostAdapter(requireContext(), postList)
        recyclerView2.adapter = adapter2

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference("recipes")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postList.clear()
                for (snapshot in dataSnapshot.children) {
                    val post = snapshot.getValue(Details::class.java)
                    post?.let { postList.add(it) }
                }
                adapter2.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        recyclerView = view.findViewById(R.id.CatagrieRv)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategriesAdapter(requireContext(), postList2)
        recyclerView.adapter = adapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postList2.clear()
                for (snapshot in dataSnapshot.children) {
                    val post = snapshot.getValue(Details::class.java)
                    post?.let { postList2.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
        return view

}



}

