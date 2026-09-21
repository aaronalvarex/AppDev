package com.example.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private RelativeLayout btnSimulator, btnRoadSigns, btnTrafficRules;
    private LinearLayout navHome, navSettings, navProfile;
    private ImageView btnMenu;


    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);

        SharedPreferences preferences =
                getSharedPreferences("AppSettings", MODE_PRIVATE);

        String username =
                preferences.getString("username", "");

        if (!username.isEmpty()) {
            tvWelcome.setText("Welcome, " + username + "!");
        }

        // Initialize Views
        btnSimulator = findViewById(R.id.btnSimulator);
        btnRoadSigns = findViewById(R.id.btnRoadSigns);
        btnTrafficRules = findViewById(R.id.btnTrafficRules);

        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // Bottom Navigation - Settings
        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        HomeActivity.this,
                        SettingsActivity.class
                );
                startActivity(intent);
            }
        });

        // Bottom Navigation - Profile
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        HomeActivity.this,
                        ProfileActivity.class
                );
                startActivity(intent);
            }
        });

        // Simulator
        btnSimulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        HomeActivity.this,
                        SimulatorActivity.class
                );
                startActivity(intent);
            }
        });

        // Road Signs
        btnRoadSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        HomeActivity.this,
                        RoadSignsActivity.class
                );
                startActivity(intent);
            }
        });

        // Traffic Rules
        btnTrafficRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        HomeActivity.this,
                        TrafficRulesActivity.class
                );
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}