package com.example.skynestapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skynestapplication.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var birdsList = mutableListOf<BirdType>()
    val LOGGING_TAG = "birdDATA"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.RecViewFieldGuide)

        thread {
            val bird = try {
                buildURLForBird()?.readText()
            } catch (e: Exception) {
                return@thread
            }
            runOnUiThread { consumeJson(bird, recyclerView) }
        }
    }

    fun consumeJson(birdsJSON: String?, recyclerView: RecyclerView) {
        if (birdsJSON != null) {
            try {
                val birds = JSONArray(birdsJSON)
                for (i in 0 until birds.length()) {
                    val observationObject = BirdType()
                    val dailyObs = birds.getJSONObject(i)

                    val name = dailyObs.getString("comName")
                    Log.i(LOGGING_TAG, "consumeJson: Name$name")
                    observationObject.comName = name

                    val sciName = dailyObs.getString("sciName")
                    Log.i(LOGGING_TAG, "consumeJson: sciName$sciName")
                    observationObject.sciName = sciName

                    birdsList.add(observationObject)
                }

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = BirdAdapter(birdsList)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}