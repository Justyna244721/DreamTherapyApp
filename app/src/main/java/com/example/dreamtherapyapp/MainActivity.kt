package com.example.dreamtherapyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : InformacjaRejestracyjna() {
    private var ButtonLogIn: Button?=null
    private var Signin: Button?=null
    private var email: EditText?=null
    private var haslo: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonLogIn = findViewById(R.id.btnLogInMainActivity)
        Signin= findViewById(R.id.btnSignInMainActivity)
        email=findViewById(R.id.edTvEmailMainActivity)
        haslo=findViewById(R.id.edTvPasswordMainActivity)

        //przycisk do strony Menu
        ButtonLogIn?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                logInRegisteredUser()
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
    private fun validateLoginDetails(): Boolean {

        return when{
            TextUtils.isEmpty(email?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_emal), true)
                false
            }

            TextUtils.isEmpty(haslo?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }

            else -> {
                //showErrorSnackBar("Your details are valid",false)
                true
            }
        }


    }
    private fun logInRegisteredUser(){


        if(validateLoginDetails()){
            val email1 = email?.text.toString().trim(){ it<= ' '}
            val password1 = haslo?.text.toString().trim(){ it<= ' '}

            //Log-in using FirebaseAuth

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email1,password1)
                .addOnCompleteListener{task ->

                    if(task.isSuccessful){
                        FireStoreClass().getUserDetails(this)
                        showErrorSnackBar("Logged in successfully", false)
                        //finish()
                        Handler().postDelayed({openMenu()},1000)

                    } else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
        }
    }
    open fun goToMainActivity() {

        val user = FirebaseAuth.getInstance().currentUser;
        val uid = user?.email.toString()

        val intent = Intent(this, StronaGlowna::class.java)
        intent.putExtra("uID",uid)
        startActivity(intent)
    }
    fun userLoggedInSuccess(user: User){

        Log.i("Imię: ", user.edTvNameSignIn)
        Log.i("Nazwisko: ", user.edTvSurnameSignIn)
        Log.i("Email: ", user.edTvEmailSignIn)
        Log.i("Wiek: ", user.edTvAgeSignIn)
        Log.i("Płeć: ", user.plec)

        goToMainActivity()
        finish()
    }

}