package com.gaston.macbook.simplemvp.presentation.show_expanded_apod_image.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.bumptech.glide.request.target.Target;
import com.gaston.macbook.simplemvp.presentation.show_expanded_apod_image.ExpandApodImageContract;
import com.gaston.macbook.simplemvp.R;
import com.github.chrisbanes.photoview.PhotoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandApodImage extends AppCompatActivity implements ExpandApodImageContract.View {

    @BindView(R.id.progressLoadingFullImage)
    ProgressBar progressLoadingFullImage;
    @BindView(R.id.imageviewApoddetail)
    PhotoView imageviewApoddetail;
    @BindView(R.id.imageview_apoddetail_reload)
    ImageView imageviewApoddetailReload;
    @BindView(R.id.textview_apoddetail_fetcherror)
    TextView textviewApoddetailFetcherror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_expand_apod_details);
        ButterKnife.bind(this);
        textviewApoddetailFetcherror.bringToFront();
        showFullScreenImage();
    }

    @Override
    public void showProgressBar() {
        progressLoadingFullImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressLoadingFullImage.setVisibility(View.GONE);
    }

    @Override
    public void showFullScreenImage() {
        showProgressBar();
        String hdUrl = PreferenceManager.getDefaultSharedPreferences(this).getString("hdurl", "defaultStringIfNothingFound");
        Glide.with(getApplicationContext()).load(hdUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                showFetchError();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
               hideProgressBar();
                return false;
            }
        }).into(imageviewApoddetail);
    }

    @Override
    public void showFetchError() {
        imageviewApoddetailReload.setVisibility(View.VISIBLE);
        textviewApoddetailFetcherror.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.imageview_apoddetail_reload)
    public void onViewClicked() {
        imageviewApoddetailReload.setVisibility(View.GONE);
        textviewApoddetailFetcherror.setVisibility(View.GONE);
    }
}
