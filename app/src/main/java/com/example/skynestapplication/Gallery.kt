package com.example.skynestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class Gallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val gridView = findViewById<GridView>(R.id.galBird)
        val imageIds = intArrayOf(
            // Add code for images in the database
        )

        val adapter = ImageAdapter(this, imageIds)
        gridView.adapter = adapter
    }
}