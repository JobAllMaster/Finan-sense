package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class LoadingScreen extends AppCompatActivity {
    private static final String TAG = "LoadingScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        Log.d(TAG, "onCreate: Loading to next Activity and Delay (5000) 5 seconds");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this,RegistrationActivity.class));
                finish();
            }
        },1000);
    }
}