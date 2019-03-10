package com.gaston.macbook.kotlinsimplemvp.presentation.show_apod

import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model.Apod

interface ApodDetailContract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideApod()

        fun showApod()

        fun showApodDetails(apodData: Apod)

        fun fetchApodDetails()

        fun showDataFetchError()

        fun reloadData()

        fun expandApodImage()

        fun showApodImageProgressBar()

        fun hideApodImageProgressBar()
    }

    interface Presenter {

        fun fetchApodData()
    }
}