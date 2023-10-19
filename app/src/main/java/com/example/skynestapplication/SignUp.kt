package com.example.skynestapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.skynestapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    //--------------------------------------------------------------------------------------------------
    //Reference: CodingSTUFF
    //URL: https://www.youtube.com/watch?v=idbxxkF1l6k
    //Use: Login and Signup using Firebase

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Authenticates user email and password from firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //Directs user to Login screen
        binding.btnGologin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //Authenticates user name, last name, email and password from firebase when user creates account
        binding.btnSignUp.setOnClickListener {
            val name = binding.tvFirstname.text.toString()
            val lastname = binding.tvLastname.text.toString()
            val email = binding.tvEmail.text.toString()
            val password = binding.tvPassword.text.toString()

            if(name.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Empty Fields are Not Allowed!", Toast.LENGTH_SHORT).show()
            }


        }


    }
}
//---------------------------------------------------------------------------------------------------------------------