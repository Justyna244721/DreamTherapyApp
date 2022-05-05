package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SignIn : AppCompatActivity() {

    private var btnSaveSignIn : Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        btnSaveSignIn = findViewById(R.id.btnSaveSignIn)
        btnSaveSignIn?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openMainActivity()
            }
        })
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}