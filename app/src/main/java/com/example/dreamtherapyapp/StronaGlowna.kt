package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

    class StronaGlowna : AppCompatActivity() {
    private var imgBtnbackFromStronaGlowna : Button?= null
    private var imgBtnFormStronaGlowna: Button?=null
    private var imgBtnProgressStronaGlowna: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_strona_glowna)
        imgBtnbackFromStronaGlowna = findViewById(R.id.btnSaveSignIn)
        imgBtnFormStronaGlowna=findViewById((R.id.imgBtnbackFromStronaGlowna))
        imgBtnProgressStronaGlowna=findViewById(R.id.imgBtnProgressStronaGlowna)
        imgBtnbackFromStronaGlowna?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openMainActivity()
            }
        })
        imgBtnFormStronaGlowna?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openAnkieta()
            }
        })
        imgBtnProgressStronaGlowna?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openProgres()
            }
        })
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

        private fun openAnkieta(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        private fun openProgres(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
}