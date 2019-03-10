package com.gaston.macbook.simplemvp.presentation.show_apod.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gaston.macbook.simplemvp.data.cache.ImageCacheImpl;
import com.gaston.macbook.simplemvp.domain.interactor.ApodDetailsInteractor;
import com.gaston.macbook.simplemvp.presentation.show_apod.ApodDetailContract;
import com.gaston.macbook.simplemvp.R;
import com.gaston.macbook.simplemvp.base.BaseActivity;
import com.gaston.macbook.simplemvp.presentation.show_apod.model.Apod;
import com.gaston.macbook.simplemvp.presentation.show_apod.presenter.ApodDetailPresenter;
import com.gaston.macbook.simplemvp.presentation.show_expanded_apod_image.view.ExpandApodImage;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApodDetail extends BaseActivity<ApodDetailPresenter> implements ApodDetailContract.View {

    @BindView(R.id.progressbar_apoddetail_loading)
    ProgressBar progressbarApoddetailLoading;
    @BindView(R.id.textview_apoddetail_fetcherror)
    TextView textviewApoddetailFetcherror;
    @BindView(R.id.imageview_apoddetail_coverimage)
    ImageView imageviewApoddetailCoverimage;
    @BindView(R.id.textview_apoddetail_title)
    TextView textviewApoddetailTitle;
    @BindView(R.id.textview_apoddetail_apoddesc)
    TextView textviewApoddetailApoddesc;
    @BindView(R.id.imageview_apoddetail_reload)
    ImageView imageviewApoddetailReload;
    @BindView(R.id.progressbar_apoddetail_image)
    ProgressBar progressbarApoddetailImage;

    @NonNull
    @Override
    protected ApodDetailPresenter createPresenter(@NonNull Context context) {
        return new ApodDetailPresenter(this, new ApodDetailsInteractor(), new ImageCacheImpl(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apod_detail);
        ButterKnife.bind(this);
        fetchApodDetails();
        imageviewApoddetailReload.bringToFront();
    }


    @Override
    public void showProgressBar() {
        progressbarApoddetailLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbarApoddetailLoading.setVisibility(View.GONE);
    }

    @Override
    public void showApodImageProgressBar() {
        progressbarApoddetailImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideApodImageProgressBar() {
        progressbarApoddetailImage.setVisibility(View.GONE);
    }

    @Override
    public void hideApod() {
        textviewApoddetailApoddesc.setVisibility(View.GONE);
        textviewApoddetailTitle.setVisibility(View.GONE);
        imageviewApoddetailCoverimage.setVisibility(View.GONE);
    }

    @Override
    public void showApod() {
        textviewApoddetailApoddesc.setVisibility(View.VISIBLE);
        textviewApoddetailTitle.setVisibility(View.VISIBLE);
        imageviewApoddetailCoverimage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApodDetails(Apod apodData) {
        showApodImageProgressBar();
        textviewApoddetailApoddesc.setText(apodData.getExplanation());
        textviewApoddetailTitle.setText(apodData.getTitle());
        Glide.with(getApplicationContext())
                .load(apodData.getLowresurl())
                .apply(RequestOptions.centerCropTransform())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        hideApodImageProgressBar();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        hideApodImageProgressBar();
                        return false;
                    }
                })
                .into(imageviewApoddetailCoverimage);
    }

    @Override
    public void fetchApodDetails() {
        presenter.fetchApodData();
    }


    @Override
    public void showDataFetchError() {
        imageviewApoddetailReload.setVisibility(View.VISIBLE);
        textviewApoddetailFetcherror.setVisibility(View.VISIBLE);
    }

    @Override
    public void reloadData() {
        textviewApoddetailFetcherror.setVisibility(View.GONE);
        imageviewApoddetailReload.setVisibility(View.GONE);
        presenter.fetchApodData();
    }

    @Override
    public void expandApodImage() {
        startActivity(new Intent(this, ExpandApodImage.class));
    }

    @OnClick(R.id.imageview_apoddetail_reload)
    public void onReloadImageClicked() {
        reloadData();
    }

    @OnClick(R.id.imageview_apoddetail_coverimage)
    public void onCoverImageClicked() {
        expandApodImage();
    }

}
