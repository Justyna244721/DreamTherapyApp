package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class SignIn : InformacjaRejestracyjna(){

    private var btnSaveSignIn : Button ?= null
    private var edTvNameSignIn: EditText?=null
    private var edTvSurnameSignIn: EditText?=null
    private var edTvEmailSignIn: EditText?=null
    private var edTvPasswordSignIn: EditText?=null
    private var edTvReapetPasswordSignIn: EditText?=null
    private var edTvAccessCodeSignIn: EditText?=null
    private var edTvAgeSignIn: EditText?=null
    private var accessCode : String ?= "1234"
    lateinit var checkBox3: CheckBox
    lateinit var checkBox4: CheckBox
    lateinit var plec: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSaveSignIn = findViewById(R.id.btnSaveSignIn)
        edTvNameSignIn=findViewById(R.id.edTvNameSignIn)
        edTvSurnameSignIn=findViewById(R.id.edTvSurnameSignIn)
        edTvEmailSignIn=findViewById(R.id.edTvEmailSignIn)
        edTvPasswordSignIn=findViewById(R.id.edTvPasswordSignIn)
        edTvReapetPasswordSignIn=findViewById(R.id.edTvReapetPasswordSignIn)
        edTvAccessCodeSignIn=findViewById(R.id.edTvAccessCodeSignIn)
        edTvAgeSignIn=findViewById(R.id.edTvAgeSignIn)
        checkBox3=findViewById(R.id.checkBox3)
        checkBox4=findViewById(R.id.checkBox4)

        btnSaveSignIn?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                registerUser()

            }
        })
        checkBox3?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (checkBox3.isChecked()) {
                    checkBox4.setEnabled(false)
                    Toast.makeText(
                        this@SignIn,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    plec="kobieta"
                }
            }
        })

        checkBox4?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (checkBox4.isChecked()) {
                    checkBox3.setEnabled(false)
                    Toast.makeText(
                        this@SignIn,
                        resources.getString(R.string.anserw_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    plec="mężczyzna"
                }
            }
        })


    }
    private fun openMenu(){
        val intent= Intent(this,StronaGlowna::class.java)
        startActivity(intent)
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun validateRegisterDetails(): Boolean {
        return when{
            TextUtils.isEmpty(edTvNameSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_name),true)
                false
            }
            TextUtils.isEmpty(edTvSurnameSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_surname),true)
                false
            }
            TextUtils.isEmpty(edTvEmailSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_emal),true)
                false
            }
            TextUtils.isEmpty(edTvPasswordSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }
            TextUtils.isEmpty(edTvReapetPasswordSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_reppassword),true)
                false
            }
            edTvPasswordSignIn?.text.toString().trim {it <= ' '} != edTvReapetPasswordSignIn?.text.toString().trim{it <= ' '} -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_mismatch),true)
                false
            }
            TextUtils.isEmpty(edTvAccessCodeSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_access_code),true)
                false
            }
            edTvAccessCodeSignIn?.text.toString().trim {it <= ' '} != accessCode?.trim{it <= ' '} -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_mismatch_access_code),true)
                false
            }
            TextUtils.isEmpty(edTvAgeSignIn?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_age),true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid",false)
                true
            }
        }
    }


    private fun registerUser(){
        if (validateRegisterDetails()){
            val login: String = edTvEmailSignIn?.text.toString().trim() {it <= ' '}
            val password: String = edTvPasswordSignIn?.text.toString().trim() {it <= ' '}

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(login,password).addOnCompleteListener(
                OnCompleteListener <AuthResult>{ task ->
                    if(task.isSuccessful){
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val user = User(
                            firebaseUser.uid,

                            edTvNameSignIn?.text.toString().trim() {it <= ' '},
                            edTvSurnameSignIn?.text.toString().trim() {it <= ' '},
                            edTvEmailSignIn?.text.toString().trim() {it <= ' '},
                            edTvAgeSignIn?.text.toString().trim() {it <= ' '},
                            plec

                            )


                        FireStoreClass().registerUser(this,user)

                        showErrorSnackBar("Twoja rejestracja przebiegła pomyślnie.",false)
                        //FirebaseAuth.getInstance().signOut()




                    } else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                    if(task.isSuccessful){
                        Handler().postDelayed({openMenu()},1000)
                    }
                }
            )
        }
    }

    fun userRegistrationSuccess() {
        Toast.makeText(
            this@SignIn,
            resources.getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()
    }
}