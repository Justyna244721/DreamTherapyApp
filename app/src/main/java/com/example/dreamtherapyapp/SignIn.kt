package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
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

        btnSaveSignIn?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                registerUser()

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
                        showErrorSnackBar("You are registered successfully. Your user id is ${firebaseUser.uid}",false)
                        openMenu()

                    } else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
            )


        }
    }
}