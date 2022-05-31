package com.example.dreamtherapyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class Ankieta : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    private var ratingBarAnkieta : RatingBar?= null
    lateinit var cBoxYesAnkietaQ2:CheckBox
    lateinit var cBoxNoAnkietaQ2: CheckBox
    lateinit var cBoxYesAnkietaQ3: CheckBox
    lateinit var cBoxNoAnkietaQ3: CheckBox
    private var switch1Tired: Switch?=null
    private var switch2Frustreated: Switch?=null
    private var switch3ItWasAllRight: Switch?=null
    private var switch4FullOfEnergy: Switch?=null
    private var alcohol: String=""
    private var SleepDrugs: String=""
    lateinit var data: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ankieta)
        ratingBarAnkieta = findViewById(R.id.ratingBarAnkieta)
        cBoxYesAnkietaQ2=findViewById(R.id.cBoxYesAnkietaQ2)
        cBoxNoAnkietaQ2=findViewById(R.id.cBoxNoAnkietaQ2)
        cBoxYesAnkietaQ3=findViewById(R.id.cBoxYesAnkietaQ3)
        cBoxNoAnkietaQ3=findViewById(R.id.cBoxNoAnkietaQ3)
        switch1Tired=findViewById(R.id.switch1Tired)
        switch2Frustreated=findViewById(R.id.switch2Frustreated)
        switch3ItWasAllRight=findViewById(R.id.switch3ItWasAllRight)
        switch4FullOfEnergy=findViewById(R.id.switch4FullOfEnergy)
        data= SimpleDateFormat("dd-mm-yyyy", Locale.getDefault()).format(Date())





        cBoxYesAnkietaQ2?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (cBoxYesAnkietaQ2.isChecked()) {
                    cBoxNoAnkietaQ2.setEnabled(false)
                    Toast.makeText(
                        this@Ankieta,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    alcohol="Alcohol comsumption. Date: $data"

                }
            }
        })
        cBoxNoAnkietaQ2?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (cBoxNoAnkietaQ2.isChecked()) {
                    cBoxYesAnkietaQ2.setEnabled(false)
                    Toast.makeText(
                        this@Ankieta,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    alcohol="No. Date: $data"
                }
            }
        })

        cBoxYesAnkietaQ3?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (cBoxYesAnkietaQ3.isChecked()) {
                    cBoxNoAnkietaQ3.setEnabled(false)
                    Toast.makeText(
                        this@Ankieta,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                SleepDrugs="Sleep drug comsumption. Date: $data"
            }
        })
        cBoxNoAnkietaQ3?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (cBoxNoAnkietaQ3.isChecked()) {
                    cBoxYesAnkietaQ3.setEnabled(false)
                    Toast.makeText(
                        this@Ankieta,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                SleepDrugs="No.Date: $data"


            }

        })


    }
    fun userAddDaraSuccess() {
        Toast.makeText(
            this@Ankieta,
            resources.getString(R.string.addData_success),
            Toast.LENGTH_SHORT
        ).show()
    }

}
