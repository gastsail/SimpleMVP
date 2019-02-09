package com.gaston.macbook.simplemvp.model;

import android.util.Log;

import com.gaston.macbook.simplemvp.BuildConfig;
import com.gaston.macbook.simplemvp.Interface.ApodApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ApodDetailsInteractor {

    public interface onDetailsFetched{
        void onSuccess(Apod apodFetchedData);
        void onFailure();
    }


    public void remoteFetch(final onDetailsFetched listener){

        String BASE_URL = "https://api.nasa.gov/planetary/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApodApiService service = retrofit.create(ApodApiService.class);

        service.getApodData(BuildConfig.APODApiKey)
                .enqueue(new Callback<Apod>() {
                    @Override
                    public void onResponse(Call<Apod> call, Response<Apod> response) {
                        if(!response.isSuccessful()){
                            listener.onFailure();
                            return;
                        }

                        Apod apodData = response.body();

                        if(apodData!=null)
                            listener.onSuccess(apodData);
                    }

                    @Override
                    public void onFailure(Call<Apod> call, Throwable t) {
                        listener.onFailure();
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });

    }

}
