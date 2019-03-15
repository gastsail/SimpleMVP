package com.gaston.macbook.simplemvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.view.WindowManager;

import com.gaston.macbook.simplemvp.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

    protected Presenter presenter;

    @NonNull
    protected abstract Presenter createPresenter(@NonNull final Context context);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayout());
        ButterKnife.bind(this);
        presenter = createPresenter(this);
    }

    public abstract
    @LayoutRes
    int getLayout();
}
