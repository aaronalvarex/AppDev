package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Toast.makeText(this, "SplashActivity: onCreate()", Toast.LENGTH_SHORT).show();

        // Maghihintay ng 2 segundo (2000 ms) bago pumasok sa HomeActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Isasara ang SplashActivity para hindi na mabalikan pag pinindot ang Back button
            }
        }, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "SplashActivity: onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "SplashActivity: onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "SplashActivity: onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "SplashActivity: onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "SplashActivity: onDestroy()", Toast.LENGTH_SHORT).show();
    }
}