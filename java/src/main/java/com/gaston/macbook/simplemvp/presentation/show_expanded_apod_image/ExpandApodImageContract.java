package com.gaston.macbook.simplemvp.presentation.show_expanded_apod_image;

public interface ExpandApodImageContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showFullScreenImage();

        void showFetchError();
    }


}
