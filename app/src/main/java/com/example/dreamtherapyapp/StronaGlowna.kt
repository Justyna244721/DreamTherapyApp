package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

    class StronaGlowna : AppCompatActivity() {
    private var imgBtnProgressStronaGlowna: ImageButton?=null
        private var imgBtnFormStronaGlowna: ImageButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strona_glowna)

        imgBtnProgressStronaGlowna=findViewById(R.id.imgBtnProgressStronaGlowna)
        imgBtnFormStronaGlowna=findViewById(R.id.imgBtnFormStronaGlowna)

        imgBtnProgressStronaGlowna?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                openProgres()
            }
        })
        imgBtnFormStronaGlowna?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                openForm()
            }
        })

    }
        private fun openProgres(){
            val intent= Intent(this,Progres::class.java)
            startActivity(intent)
        }
        private fun openForm(){
            val intent= Intent(this,Ankieta::class.java)
            startActivity(intent)
        }

    }