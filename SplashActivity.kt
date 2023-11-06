package com.example.newrecipe.Activity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.airbnb.lottie.LottieAnimationView
import com.example.newrecipe.R

class SplashActivity : Activity() {

    private val SPLASH_DURATION: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val lottieView = findViewById<LottieAnimationView>(R.id.lottieAnimationView2)
//        lottieView.setAnimation("animation.json")
//        lottieView.playAnimation()

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginAcitvity::class.java)
            startActivity(intent)

            finish()
        }, SPLASH_DURATION)
    }
}

