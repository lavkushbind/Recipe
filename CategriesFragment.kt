package com.example.newrecipe.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipe.Adapter.CategriesAdapter
import com.example.newrecipe.Adapter.FovoriteAdapter
import com.example.newrecipe.Adapter.Like_adapter
import com.example.newrecipe.Adapter.PostAdapter
import com.example.newrecipe.Model.Details
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R

class CategriesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Like_adapter
    private val postList = ArrayList<Details>()
    private lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = Like_adapter(requireContext(), postList)


        val rootView = inflater.inflate(R.layout.fragment_categries, container, false)
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");



//        recyclerView = rootView.findViewById(R.id.recyclerView)
//        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.layoutManager = layoutManager


        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

//        val adapter = YourAdapter(postList)  // Replace YourAdapter with your actual adapter class
        recyclerView.adapter = adapter




//        adapter = FovoriteAdapter(requireContext(), postList)
//        recyclerView.adapter = adapter

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference("recipes")


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postList.clear()
                for (snapshot in dataSnapshot.children) {
                    val post = snapshot.getValue(Details::class.java)
                    post?.let { postList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        return rootView
    }
}