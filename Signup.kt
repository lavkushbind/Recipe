package com.example.newrecipe.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.newrecipe.Model.User
import com.example.newrecipe.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var useri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        val signupbtn = findViewById<Button>(R.id.signUp)
        val name = findViewById<TextInputEditText>(R.id.tname)
        val userid = findViewById<TextInputEditText>(R.id.tuserId)
        val Email = findViewById<TextInputEditText>(R.id.temail)
        val phone = findViewById<TextInputEditText>(R.id.tphone)
        val password = findViewById<TextInputEditText>(R.id.tpass)

//        if (userId != null) {
//            val intent = Intent(this, Home_Activity::class.java)
//            startActivity(intent)
//            finish()
//        }

        signupbtn.setOnClickListener {
            val Name = name.text.toString()
            var userId = userid.text.toString()
            val email = Email.text.toString()
            val Password = password.text.toString()
//            val user = User(Name, userId, email, Password,"","")
//            database=FirebaseDatabase.getInstance().getReference("Users")
//            database.child(userId).setValue(user).addOnSuccessListener {
//                name.text?.clear()
//                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener {
//                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
//            }
            auth.createUserWithEmailAndPassword(email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = User(Name, userId, email, Password,"","","")
                        val userio = auth.currentUser
//                        auth = FirebaseAuth.getInstance()
                        useri = auth.currentUser?.uid ?: ""

                        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(Name).build()
                        userio?.updateProfile(profileUpdates)
                        database=FirebaseDatabase.getInstance().getReference("Users")
                        database.child(useri).setValue(user).addOnSuccessListener {
                            name.text?.clear()
                            Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, Home_Activity::class.java)
                            startActivity(intent)
                            finish()

                        }.addOnFailureListener {
                            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                        }

//                        val intent = Intent(this, Home_Activity::class.java)
//                            startActivity(intent)
//                            finish()


                        Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }



        val signin = findViewById<TextView>(R.id.signIn)
        signin.setOnClickListener {
            val opensignIn = Intent(this, LoginActivity2::class.java)
            startActivity(opensignIn)
        }
    }
}
