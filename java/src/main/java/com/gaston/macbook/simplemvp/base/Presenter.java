package com.gaston.macbook.simplemvp.base;

import com.gaston.macbook.simplemvp.presentation.show_apod.ApodDetailContract;

/**
 * Created by Gastón Saillén on 15 March 2019
 */
public interface Presenter<V extends ApodDetailContract.View> {

    void attachView(V view);

    void detachView();
}