package com.example.skynestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.skynestapplication.databinding.ActivityProfileBinding
import com.example.skynestapplication.databinding.ActivitySignUpBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var firebaseAuth : FirebaseAuth

    private lateinit var name: EditText

    private lateinit var surname: EditText

    private lateinit var username: TextView
    private lateinit var email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigate()

        //Directs user to Login screen
        binding.btnSignOut.setOnClickListener {
            val intent = Intent(this, Login::class.java)
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