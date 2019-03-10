package com.gaston.macbook.kotlinsimplemvp.domain.interactor

import android.util.Log
import com.gaston.macbook.kotlinsimplemvp.BuildConfig
import com.gaston.macbook.kotlinsimplemvp.data.net.ApodApiService
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model.Apod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApodDetailsInteractor {

    companion object {
        const val TAG: String = "ApodDetailInteractor"
        const val BASE_URL = "https://api.nasa.gov/planetary/"
    }

    interface onDetailsFetched {
        fun onSuccess(apodFetchedData : Apod)
        fun onFailure()
    }


    fun getApodDataFromRemote(listener : onDetailsFetched){

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApodApiService::class.java)
        service.getApodData(BuildConfig.APODApiKey)
                .enqueue(object : Callback<Apod> {
                    override fun onResponse(call: Call<Apod>, response: Response<Apod>) {
                        if (!response.isSuccessful) {
                            listener.onFailure()
                            return
                        }

                        val apodData = response.body()

                        if (apodData != null)
                            listener.onSuccess(apodData)
                    }

                    override fun onFailure(call: Call<Apod>, t: Throwable) {
                        listener.onFailure()
                        Log.e(TAG, "onFailure: " + t.cause)
                    }
                })

    }

}