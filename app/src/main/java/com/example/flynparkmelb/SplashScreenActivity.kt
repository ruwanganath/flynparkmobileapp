package com.example.flynparkmelb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

// Splash screen activity class
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        imageView = findViewById(R.id.imageViewLogo)
        val beatLogo_on: Animation = AnimationUtils.loadAnimation(this, R.anim.beat_on)
        val beatLogo_off: Animation = AnimationUtils.loadAnimation(this, R.anim.beat_off)
        beatLogo_on.duration = 250
        beatLogo_off.duration = 250
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 500)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 1000)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 1500)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 2000)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 2500)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 3000)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 3500)
        imageView.startAnimation(beatLogo_on)
        Handler().postDelayed({
            imageView.startAnimation(beatLogo_off)
        }, 4000)
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
   }
}