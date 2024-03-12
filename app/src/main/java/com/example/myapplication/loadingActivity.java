package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class loadingActivity extends AppCompatActivity {
    private static final String TAG = "LoadingScreen";
    TextView loadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

        Log.d(TAG, "onCreate: Loading to next Activity and Delay (5000) 5 seconds");

        loadText = findViewById(R.id.RandomText);
        Log.d(TAG, "onCreate: Calls random text function");
        loadText.setText(generateRandomText());

        Handler handler = new Handler();
        Log.d(TAG, "onCreate: Is the loading function");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(loadingActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);



    }


    private String generateRandomText() {
        Log.d(TAG, "generateRandomText: Generates random texts for loading");
        final String[] randomTexts = {
                "Please standby!",
                "Bear with us!",
                "Creating your tracker!"
        };
        return randomTexts[new Random().nextInt(randomTexts.length)];
    }
}
