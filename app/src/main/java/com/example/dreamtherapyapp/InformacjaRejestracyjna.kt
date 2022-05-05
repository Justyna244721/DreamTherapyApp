package com.example.dreamtherapyapp

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


open class InformacjaRejestracyjna: AppCompatActivity() {

    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackbar =
            Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view

        if (errorMessage) {
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(this@InformacjaRejestracyjna,
                    R.color.colorSnackBarError
                )
            )
        }else{
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(this@InformacjaRejestracyjna,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackbar.show()
    }
}