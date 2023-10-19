package com.example.skynestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringArrayResource

class Features : AppCompatActivity() {

    private lateinit var backgroundImageView: ImageView
    private lateinit var btnSkip: Button
    private lateinit var tvMessage: TextView
    private var imageIndex = 1
    private val imageArray = intArrayOf(R.drawable.bird1, R.drawable.bird2, R.drawable.bird3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_features)

        backgroundImageView = findViewById(R.id.backgroundImageView)
        btnSkip = findViewById(R.id.btnSkip)
        //tvMessage = findViewById(R.id.tvMessage)

        backgroundImageView.setOnClickListener {
            // Change the background image
            imageIndex = (imageIndex + 1) % imageArray.size
            backgroundImageView.setImageResource(imageArray[imageIndex])

            btnSkip.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }
}
