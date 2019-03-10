package com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.gaston.macbook.kotlinsimplemvp.R
import com.gaston.macbook.kotlinsimplemvp.base.BaseActivity
import com.gaston.macbook.kotlinsimplemvp.data.cache.ImageCacheImpl
import com.gaston.macbook.kotlinsimplemvp.domain.interactor.ApodDetailsInteractor
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.ApodDetailContract
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model.Apod
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.presenter.ApodDetailPresenter
import com.gaston.macbook.kotlinsimplemvp.presentation.show_expanded_apod_image.view.ExpandApodImage
import kotlinx.android.synthetic.main.activity_apod_detail.*

class ApodDetail : BaseActivity<ApodDetailPresenter>(), ApodDetailContract.View {

    override fun createPresenter(context: Context): ApodDetailPresenter {
    return ApodDetailPresenter(this, ApodDetailsInteractor(), ImageCacheImpl(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod_detail)
        fetchApodDetails()
        imageview_apoddetail_reloadDetail.bringToFront()
    }

    override fun showProgressBar() {
        progressbar_apoddetail_loading.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressbar_apoddetail_loading.visibility = View.GONE
    }

    override fun showApodImageProgressBar() {
        progressbar_apoddetail_image.visibility = View.VISIBLE
    }

    override fun hideApodImageProgressBar() {
        progressbar_apoddetail_image.visibility = View.GONE
    }

    override fun hideApod() {
        textview_apoddetail_apoddesc.visibility = View.GONE
        textview_apoddetail_title.visibility = View.GONE
        imageview_apoddetail_coverimage.visibility = View.GONE
    }

    override fun showApod() {
        textview_apoddetail_apoddesc.visibility = View.VISIBLE
        textview_apoddetail_title.visibility = View.VISIBLE
        imageview_apoddetail_coverimage.visibility = View.VISIBLE
    }

    override fun showApodDetails(apodData: Apod) {
        showApodImageProgressBar()
        textview_apoddetail_apoddesc.setText(apodData.explanation)
        textview_apoddetail_title.setText(apodData.title)
        Glide.with(applicationContext)
                .load(apodData.lowresurl)
                .apply(RequestOptions.centerCropTransform())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        hideApodImageProgressBar()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        hideApodImageProgressBar()
                        return false
                    }
                })
                .into(imageview_apoddetail_coverimage)
    }

    override fun fetchApodDetails() {
        presenter.fetchApodData()
    }

    override fun showDataFetchError() {
        imageview_apoddetail_reloadDetail.visibility = View.VISIBLE
        textview_apoddetail_fetcherrorDetail.visibility = View.VISIBLE
    }

    override fun reloadData() {
        textview_apoddetail_fetcherrorDetail.visibility = View.GONE
        imageview_apoddetail_reloadDetail.visibility = View.GONE
        presenter.fetchApodData()
    }

    override fun expandApodImage() {
        startActivity(Intent(this,ExpandApodImage::class.java))
    }

    fun reloadApodDetails(view: View){
        reloadData()
    }

    fun apodCoverImageClick(view: View){
        expandApodImage()
    }

}
