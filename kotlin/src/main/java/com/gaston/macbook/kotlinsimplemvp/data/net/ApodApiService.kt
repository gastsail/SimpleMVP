package com.gaston.macbook.kotlinsimplemvp.data.net

import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model.Apod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApiService {

    @GET("apod")
    fun getApodData(@Query("api_key") API_KEY: String): Call<Apod>
}