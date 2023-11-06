package com.example.newrecipe.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.newrecipe.R

class SubmitActivity : AppCompatActivity() {
    val button = findViewById<Button>(R.id.button5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loging)
        button.setOnClickListener {
            val button = Intent(this, Signup::class.java)
            startActivity(button)
        }

    }
}