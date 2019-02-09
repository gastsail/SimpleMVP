package com.gaston.macbook.simplemvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gaston.macbook.simplemvp.base.BaseActivity;
import com.gaston.macbook.simplemvp.Interface.ApodDetailView;
import com.gaston.macbook.simplemvp.model.Apod;
import com.gaston.macbook.simplemvp.model.ApodDetailsInteractor;
import com.gaston.macbook.simplemvp.presenter.ApodDetailPresenter;
import com.gaston.macbook.simplemvp.R;

public class ApodDetail extends BaseActivity<ApodDetailPresenter> implements ApodDetailView {

    private ImageView coverImgView, reloadImgView;
    private TextView detailTextView, titleTextView, fetchErrorTextView;
    private ProgressBar loadingProgressBar;

    @NonNull
    @Override
    protected ApodDetailPresenter createPresenter(@NonNull Context context) {
        return new ApodDetailPresenter(this, new ApodDetailsInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apod_detail);

        bindViews();
        fetchApodDetails();

        reloadImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });

    }


    public void bindViews() {
        coverImgView = findViewById(R.id.imageview_apoddetail_coverimage);
        reloadImgView = findViewById(R.id.imageview_apoddetail_reload);
        reloadImgView.bringToFront();
        detailTextView = findViewById(R.id.textview_apoddetail_apoddesc);
        fetchErrorTextView = findViewById(R.id.textview_apoddetail_fetcherror);
        titleTextView = findViewById(R.id.textview_apoddetail_title);
        loadingProgressBar = findViewById(R.id.progressbar_apoddetail_loading);
    }


    @Override
    public void showProgressBar() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideApod() {
        detailTextView.setVisibility(View.GONE);
        titleTextView.setVisibility(View.GONE);
        coverImgView.setVisibility(View.GONE);
    }

    @Override
    public void showApod() {
        detailTextView.setVisibility(View.VISIBLE);
        titleTextView.setVisibility(View.VISIBLE);
        coverImgView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApodDetails(Apod apodDetails) {

        detailTextView.setText(apodDetails.getExplanation());
        titleTextView.setText(apodDetails.getTitle());

        Glide.with(this)
                .load(apodDetails.getLowresurl())
                .apply(RequestOptions.centerCropTransform())
                .into(coverImgView);
    }

    @Override
    public void fetchApodDetails() {
        mPresenter.fetchData();
    }


    @Override
    public void showDataFetchError() {
        reloadImgView.setVisibility(View.VISIBLE);
        fetchErrorTextView.setVisibility(View.VISIBLE);

    }

    @Override
    public void reloadData() {
        fetchErrorTextView.setVisibility(View.GONE);
        reloadImgView.setVisibility(View.GONE);
        mPresenter.fetchData();
    }
}
