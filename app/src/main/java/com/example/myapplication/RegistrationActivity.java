package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        Log.d(TAG, "onCreate: Starting");

        Button btnNav = (Button) findViewById(R.id.button);

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked btnNav");

                Intent firstintent = new Intent(RegistrationActivity.this, loadingActivity.class);
                startActivity(firstintent);

            }
        });
    }

}