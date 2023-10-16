package com.example.skynestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.skynestapplication.databinding.ActivityLoginBinding
import com.example.skynestapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    //--------------------------------------------------------------------------------------------------
    //Reference: CodingSTUFF
    //URL: https://www.youtube.com/watch?v=idbxxkF1l6k
    //Use: Login and Signup using Firebase

    private lateinit var binding : ActivityLoginBinding

    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Authenticates user email and password from firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //Directs user to Signup screen
        binding.btnGoSignup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        //Authenticates user email and password from firebase when user tries to login
        binding.btnLogin.setOnClickListener {
            val email = binding.tvEmail.text.toString()
            val password = binding.tvPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, HomeDashboard::class.java)
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
//---------------------------------------------------------------------------------------------------------