package com.example.skynestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.skynestapplication.databinding.ActivityHomeDashboardBinding
import com.example.skynestapplication.databinding.ActivityProfileBinding

class HomeDashboard : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigate()


        //Will take user to the Map screen after clicking
        binding.ivHotsopt.setOnClickListener {
            val intent = Intent(this, Hotspots::class.java)
            startActivity(intent)
        }


    }


    //--------------------------------------------------------------------------------------------------
    //Reference: Mafia Codes
    //URL: https://www.youtube.com/watch?v=oeKtwd1DBfg
    //Use: The bottom navigation bar will allow users to navigate through app
    private fun navigate(){

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.i_home -> {
                    val intent = Intent(this, HomeDashboard::class.java)
                    startActivity(intent)

                }
                R.id.i_profile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)

                }
                R.id.i_favourite -> {
                    val intent = Intent(this, HomeDashboard::class.java)
                    startActivity(intent)


                }
                else -> {}
            }
            true
        }

    }

}