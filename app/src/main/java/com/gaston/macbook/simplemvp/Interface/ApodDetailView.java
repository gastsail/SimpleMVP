package com.gaston.macbook.simplemvp.Interface;

import com.gaston.macbook.simplemvp.model.Apod;

import java.util.List;

public interface ApodDetailView {

    void showProgressBar();

    void hideProgressBar();

    void hideApod();

    void showApod();

    void showApodDetails(Apod apodList);

    void fetchApodDetails();

    void showDataFetchError();

    void reloadData();
}
