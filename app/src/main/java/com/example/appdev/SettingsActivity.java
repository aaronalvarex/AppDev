package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout navHome, navSettings, navProfile;

    private RelativeLayout btnHowToUse,
            btnPrivacyPolicy,
            btnTerms,
            btnAbout,
            btnReportIssue;

    private SwitchCompat switchSound;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupSoundPreference();
        setupClickListeners();
    }

    private void initViews() {

        // Sound Toggle Switch
        switchSound = findViewById(R.id.switchSound);

        // Bottom Navigation
        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // Settings Buttons
        btnHowToUse = findViewById(R.id.btnHowToUse);
        btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
        btnTerms = findViewById(R.id.btnTerms);
        btnAbout = findViewById(R.id.btnAbout);
        btnReportIssue = findViewById(R.id.btnReportIssue);
    }

    private void setupSoundPreference() {
        preferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        boolean soundEnabled = preferences.getBoolean("sound_enabled", true);
        switchSound.setChecked(soundEnabled);

        // Mino-save lang ang state (ON/OFF) para sa Simulator
        switchSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("sound_enabled", isChecked);
            editor.apply();
        });
    }

    private void setupClickListeners() {

        // How to Use
        btnHowToUse.setOnClickListener(v ->
                Toast.makeText(
                        SettingsActivity.this,
                        "How to Use section",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // Privacy Policy
        btnPrivacyPolicy.setOnClickListener(v ->
                Toast.makeText(
                        SettingsActivity.this,
                        "Privacy Policy section",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // Terms of Service
        btnTerms.setOnClickListener(v ->
                Toast.makeText(
                        SettingsActivity.this,
                        "Terms of Service section",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // About
        btnAbout.setOnClickListener(v ->
                Toast.makeText(
                        SettingsActivity.this,
                        "DriveSmart PH v1.0.0",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // Report an Issue
        btnReportIssue.setOnClickListener(v ->
                Toast.makeText(
                        SettingsActivity.this,
                        "Report an Issue section",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // Home Navigation
        navHome.setOnClickListener(v -> {
            finish();
        });

        // Profile Navigation
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SettingsActivity.this,
                    ProfileActivity.class
            );

            startActivity(intent);
            finish();
        });
    }
}