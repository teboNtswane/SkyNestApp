package com.example.skynestapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class About : AppCompatActivity() {

    private lateinit var nextFeatures: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        nextFeatures = findViewById(R.id.btn_nextFeatures)

        //Will take user to the About screen after clicking
        nextFeatures.setOnClickListener {
            val intent = Intent(this, Features::class.java)
            startActivity(intent)
        }

    }
}