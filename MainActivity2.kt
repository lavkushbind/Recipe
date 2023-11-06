package com.example.newrecipe.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecipe.Adapter.MyAdapter
import com.example.newrecipe.Model.Recipe_model
import com.example.newrecipe.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {

    private  lateinit var dbref: DatabaseReference

    private lateinit var RecyclerView: RecyclerView
    private  lateinit var userArrayList:ArrayList<Recipe_model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        RecyclerView = findViewById(R.id.RecView)

        RecyclerView.layoutManager=LinearLayoutManager(this)
        RecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf<Recipe_model>()
//        getUserData()
    }

//    private fun getUserData() {
//        dbref = FirebaseDatabase.getInstance().getReference("recipes")
//        dbref.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()) {
//                    for (userSanpshot in snapshot.children){
//                        val Recipe_model =userSanpshot.getValue(Recipe_model::class.java)
//
//                        userArrayList.add(Recipe_model!!)
//
//                    }
//                    RecyclerView.adapter=MyAdapter(userArrayList)
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//    }
}