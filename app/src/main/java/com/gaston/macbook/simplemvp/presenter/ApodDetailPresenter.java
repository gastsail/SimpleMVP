package com.gaston.macbook.simplemvp.presenter;

import android.support.annotation.NonNull;

import com.gaston.macbook.simplemvp.base.BasePresenter;
import com.gaston.macbook.simplemvp.Interface.ApodDetailView;
import com.gaston.macbook.simplemvp.model.Apod;
import com.gaston.macbook.simplemvp.model.ApodDetailsInteractor;

import java.util.List;

public class ApodDetailPresenter extends BasePresenter implements ApodDetailsInteractor.onDetailsFetched {

    private ApodDetailView mView;
    private ApodDetailsInteractor mApodInteractor;

    public ApodDetailPresenter(@NonNull ApodDetailView view, @NonNull ApodDetailsInteractor mApodInteractor) {
        this.mView = view;
        this.mApodInteractor = mApodInteractor;

    }


    public void fetchData() {
        mView.hideApod();
        mView.showProgressBar();
        mApodInteractor.remoteFetch( this);
    }


    @Override
    public void onSuccess(Apod apodFetchedData) {
        mView.hideProgressBar();
        mView.showApod();
        mView.showApodDetails(apodFetchedData);
    }

    @Override
    public void onFailure() {
        mView.showDataFetchError();
        mView.hideProgressBar();
    }
}
