package com.example.newrecipe.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.example.newrecipe.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginAcitvity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_loging)

//        val lottieView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
//        lottieView.setAnimation("animation.json")
//        lottieView.playAnimation()
        var  signupbtn = findViewById<Button>(R.id.Signupbtn)
         var login = findViewById<Button>(R.id.loginbtn)

        val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)
            finish()
        }

        signupbtn.setOnClickListener {
            val signupbtn = Intent(this, Signup::class.java)
            startActivity(signupbtn)
        }
        login.setOnClickListener {
            val login = Intent(this, LoginActivity2::class.java)
            startActivity(login)
        }
    }
}

