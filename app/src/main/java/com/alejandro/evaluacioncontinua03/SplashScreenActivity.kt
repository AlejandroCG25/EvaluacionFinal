package com.alejandro.evaluacioncontinua03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat
import com.alejandro.evaluacioncontinua03.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        HandlerCompat.postDelayed(Handler(),{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },null,3000 )
    }
}