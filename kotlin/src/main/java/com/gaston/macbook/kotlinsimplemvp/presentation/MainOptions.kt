package com.gaston.macbook.kotlinsimplemvp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.gaston.macbook.kotlinsimplemvp.R
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.view.ApodDetail
import com.gaston.macbook.kotlinsimplemvp.presentation.show_live_iss.view.ISSLive

class MainOptions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main_options)
    }

    fun apodAction(view: View){
        navigateToActivity(ApodDetail::class.java)
    }

    fun issAction(view: View){
        navigateToActivity(ISSLive::class.java)
    }

    fun navigateToActivity(activity: Class<*>){
        startActivity(Intent(this,activity))
    }
}
