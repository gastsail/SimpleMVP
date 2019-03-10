package com.gaston.macbook.kotlinsimplemvp.presentation.show_expanded_apod_image.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gaston.macbook.kotlinsimplemvp.R
import com.gaston.macbook.kotlinsimplemvp.presentation.show_expanded_apod_image.ExpandApodImageContract
import kotlinx.android.synthetic.main.activity_apod_detail.*
import kotlinx.android.synthetic.main.activity_expand_apod_image.*

class ExpandApodImage : AppCompatActivity(), ExpandApodImageContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_expand_apod_image)
        textview_apoddetail_fetcherror.bringToFront()
        showFullScreenImage()
    }

    override fun showProgressBar() {
        progressLoadingFullImage.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressLoadingFullImage.visibility = View.GONE
    }

    override fun showFullScreenImage() {
        showProgressBar()
        val hdUrl = PreferenceManager.getDefaultSharedPreferences(this).getString("hdurl","defaultString")
        Glide.with(applicationContext).load(hdUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                showFetchError()
                return false
            }

            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                hideProgressBar()
                return false
            }
        }).into(imageviewApoddetail)
    }

    override fun showFetchError() {
        imageview_apoddetail_reload.visibility = View.VISIBLE
        textview_apoddetail_fetcherror.visibility = View.VISIBLE
    }

    fun apodReload(view:View){
        imageview_apoddetail_reload.visibility = View.GONE
        textview_apoddetail_fetcherror.visibility = View.GONE
    }

}
