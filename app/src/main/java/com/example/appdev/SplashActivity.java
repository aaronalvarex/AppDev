package com.example.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            SharedPreferences preferences =
                    getSharedPreferences("AppSettings", MODE_PRIVATE);

            String username =
                    preferences.getString("username", "");

            if (username.isEmpty()) {

                // First time user
                Intent intent = new Intent(
                        SplashActivity.this,
                        UsernameActivity.class
                );

                startActivity(intent);

            } else {

                // Username already saved
                Intent intent = new Intent(
                        SplashActivity.this,
                        HomeActivity.class
                );

                startActivity(intent);
            }

            finish();

        }, SPLASH_TIME);
    }
}