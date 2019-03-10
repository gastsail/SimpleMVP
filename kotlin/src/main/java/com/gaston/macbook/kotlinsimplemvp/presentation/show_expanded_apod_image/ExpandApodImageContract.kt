package com.gaston.macbook.kotlinsimplemvp.presentation.show_expanded_apod_image

interface ExpandApodImageContract {

    interface View {

        fun showProgressBar()

        fun hideProgressBar()

        fun showFullScreenImage()

        fun showFetchError()
    }
}