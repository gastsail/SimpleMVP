package com.gaston.macbook.simplemvp.Interface;

import com.gaston.macbook.simplemvp.model.Apod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodApiService {

    @GET("apod")
    Call<Apod> getApodData(@Query("api_key") String API_KEY);
}
