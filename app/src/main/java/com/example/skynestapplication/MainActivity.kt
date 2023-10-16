package com.example.skynestapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var nextAbout: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextAbout = findViewById(R.id.btn_nextAbout)

        //Will take user to the About screen after clicking
        nextAbout.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }


    }
}