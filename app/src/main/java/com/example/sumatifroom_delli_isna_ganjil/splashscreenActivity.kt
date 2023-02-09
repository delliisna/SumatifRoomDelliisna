package com.example.sumatifroom_delli_isna_ganjil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class splashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

         Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@splashscreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}
