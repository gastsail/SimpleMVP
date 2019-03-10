package com.gaston.macbook.simplemvp.data.net;

import com.gaston.macbook.simplemvp.presentation.show_apod.model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodApiService {

    @GET("apod")
    Call<Apod> getApodData(@Query("api_key") String API_KEY);
}
