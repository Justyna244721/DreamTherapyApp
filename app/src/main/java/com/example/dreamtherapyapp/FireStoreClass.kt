package com.example.dreamtherapyapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class FireStoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()
    private val data= FirebaseDatabase.getInstance()
    private var dataBaseReference= data.getReference()

    fun addDatafromForm(activity: Ankieta,dana:String){


        mFireStore.collection(Constans.USERS).add(dana).addOnSuccessListener {

            activity.userAddDaraSuccess()
        }
            .addOnFailureListener { exceotion->
                Log.w(TAG,"PROBLEM Z DODANIEM DOKUMENTU")
            }
    }
    fun registerUser(activity: SignIn, userInfo: User) {
        //mFireStore.collection("users")
        mFireStore.collection(Constans.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Problem z rejestracją użytkownika",
                    e
                )
            }

    }
    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
    fun getUserDetails(activity: Activity){
        mFireStore.collection(Constans.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(activity.javaClass.simpleName,document.toString())
                val user = document.toObject(User::class.java)!!

                when(activity){
                    is MainActivity -> {
                        activity.userLoggedInSuccess(user)
                    }


                }
            }
    }

}