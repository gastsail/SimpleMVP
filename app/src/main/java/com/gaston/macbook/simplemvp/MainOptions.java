package com.gaston.macbook.simplemvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gaston.macbook.simplemvp.view.ApodDetail;
import com.gaston.macbook.simplemvp.view.ISSLive;


public class MainOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_options);
    }

    public void apodAction(View view) {
        navigateToActivity(ApodDetail.class);
    }

    public void issAction(View view) {
        navigateToActivity(ISSLive.class);
    }


    private void navigateToActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }
}
