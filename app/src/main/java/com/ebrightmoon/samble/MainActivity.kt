package com.ebrightmoon.samble

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        btnOpenGl.setOnClickListener {
            startActivity(Intent(this, OpenGLActivity::class.java))
        }

        airHockey.setOnClickListener {
            startActivity(Intent(this, AirHockeyActivity::class.java))
        }

        particles.setOnClickListener {
            startActivity(Intent(this, ParticlesActivity::class.java))
        }
    }
}