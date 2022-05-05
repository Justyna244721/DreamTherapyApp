package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var ButtonLogIn: Button?=null
    private var Signin: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonLogIn = findViewById(R.id.btnLogInMainActivity)
        Signin= findViewById(R.id.btnSignInMainActivity)

        //przycisk do strony Menu
        ButtonLogIn?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openMenu()
            }
        })

        //przycisk do strony z Rejestracją
        Signin?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openSignIn()
            }
        })
    }
    //Przejście do strony Głównej
    private fun openMenu(){
        val intent= Intent(this,StronaGlowna::class.java)
        startActivity(intent)
    }
    //Przejscie do strony z Rejestracją
    private fun openSignIn(){
        val intent= Intent(this,SignIn::class.java)
        startActivity(intent)
    }
}