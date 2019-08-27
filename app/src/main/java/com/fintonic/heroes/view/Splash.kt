package com.fintonic.heroes.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, Heroes::class.java))
        finishAffinity()
    }

}