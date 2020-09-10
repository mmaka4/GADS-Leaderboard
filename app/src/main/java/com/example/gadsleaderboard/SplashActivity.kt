package com.example.gadsleaderboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.splash_layout)

        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}