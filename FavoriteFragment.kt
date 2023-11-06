package com.example.newrecipe.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipe.Adapter.FovoriteAdapter
import com.example.newrecipe.Adapter.Like_adapter
import com.example.newrecipe.Adapter.PostAdapter
import com.example.newrecipe.Model.Details
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R
import com.example.newrecipe.databinding.FragmentFavoriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteFragment : Fragment() {
     private lateinit var recyclerView: RecyclerView
        private lateinit var adapter: Like_adapter
        private val postList = ArrayList<Details>()
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var databaseReference: DatabaseReference


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
            databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
            recyclerView = rootView.findViewById(R.id.RecycleRview)
            val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager

            adapter = Like_adapter(requireContext(), postList)
            recyclerView.adapter = adapter
            firebaseAuth = FirebaseAuth.getInstance()

            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            val userId = currentUser?.uid
            val firebaseDatabase = FirebaseDatabase.getInstance()
//            val databaseReference = firebaseDatabase.reference.child("users").child(userId).child("save")


            if (userId != null) {
                val firebaseDatabase = FirebaseDatabase.getInstance()
                val databaseReference = firebaseDatabase.reference.child("Users").child(userId).child("Save")

                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        postList.clear()
                        for (postSnapshot in dataSnapshot.children) {
                            val postId = postSnapshot.key

                            if (postId != null) {
                                val postRef = firebaseDatabase.reference.child("recipes")

                                postRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        postList.clear()
                                        for (snapshot in dataSnapshot.children) {

                                            val post = snapshot.getValue(Details::class.java)
                                            if (post?.postId == postId ) {
                                                post?.let { postList.add(it) }
                                            }
                                        }


                                        adapter.notifyDataSetChanged()
                                    }


//                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                                        val postData = dataSnapshot.getValue(Details::class.java)
//                                        if (postData != null) {
//                                            // Add postData to your postList
//                                            postList.add(postData.toDetails()) // Modify as needed
//
//                                            // Notify the adapter that data has changed
//                                            adapter.notifyDataSetChanged()
//                                        }
//                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        Log.e("Firebase", "Error fetching data: ${databaseError.message}")
                                    }
                                })
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("Firebase", "Error fetching data: ${databaseError.message}")
                    }
                })
            }

            return rootView
        }
    }