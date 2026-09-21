package com.example.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    // Bottom Navigation
    private LinearLayout navHome;
    private LinearLayout navSettings;
    private LinearLayout navProfile;

    // Profile Buttons
    private RelativeLayout btnSignOut;
    private RelativeLayout btnEditProfile;
    private RelativeLayout btnClearData;

    // Profile Name
    private TextView tvProfileName;

    // Simulator Scores
    private TextView tvTrafficLightScore;
    private TextView tvPedestrianScore;
    private TextView tvRightOfWayScore;
    private TextView tvLaneUseScore;

    // Shared Preferences
    private SharedPreferences appPreferences;
    private SharedPreferences simulatorPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // =========================
        // SHARED PREFERENCES
        // =========================

        appPreferences = getSharedPreferences(
                "AppSettings",
                MODE_PRIVATE
        );

        simulatorPreferences = getSharedPreferences(
                "SimulatorScores",
                MODE_PRIVATE
        );

        // =========================
        // FIND VIEWS
        // =========================

        tvProfileName = findViewById(R.id.tvProfileName);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnClearData = findViewById(R.id.btnClearData);
        btnSignOut = findViewById(R.id.btnSignOut);

        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // Simulator score TextViews
        tvTrafficLightScore =
                findViewById(R.id.tvTrafficLightScore);

        tvPedestrianScore =
                findViewById(R.id.tvPedestrianScore);

        tvRightOfWayScore =
                findViewById(R.id.tvRightOfWayScore);

        tvLaneUseScore =
                findViewById(R.id.tvLaneUseScore);

        // =========================
        // LOAD USERNAME
        // =========================

        loadUsername();

        // =========================
        // LOAD SIMULATOR SCORES
        // =========================

        loadSimulatorScores();

        // =========================
        // EDIT PROFILE
        // =========================

        btnEditProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProfileActivity.this,
                    EditProfileActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // CLEAR DATA
        // =========================

        btnClearData.setOnClickListener(v -> {

            new AlertDialog.Builder(ProfileActivity.this)
                    .setTitle("Clear Data")
                    .setMessage(
                            "Are you sure you want to clear your data? " +
                                    "This will remove your username and simulator scores."
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .setPositiveButton(
                            "Clear Data",
                            (dialog, which) -> {

                                // Clear username
                                appPreferences.edit()
                                        .clear()
                                        .apply();

                                // Clear simulator scores
                                simulatorPreferences.edit()
                                        .clear()
                                        .apply();

                                // Return to Username screen
                                Intent intent = new Intent(
                                        ProfileActivity.this,
                                        UsernameActivity.class
                                );

                                intent.setFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                );

                                startActivity(intent);
                                finish();
                            }
                    )
                    .show();
        });

        // =========================
        // SIGN OUT
        // =========================

        btnSignOut.setOnClickListener(v -> {

            new AlertDialog.Builder(ProfileActivity.this)
                    .setTitle("Sign Out")
                    .setMessage(
                            "Are you sure you want to sign out?"
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .setPositiveButton(
                            "Sign Out",
                            (dialog, which) -> {

                                // Remove username only
                                appPreferences.edit()
                                        .remove("username")
                                        .apply();

                                Intent intent = new Intent(
                                        ProfileActivity.this,
                                        UsernameActivity.class
                                );

                                intent.setFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                );

                                startActivity(intent);
                                finish();
                            }
                    )
                    .show();
        });

        // =========================
        // HOME
        // =========================

        navHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProfileActivity.this,
                    HomeActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);
            finish();
        });

        // =========================
        // SETTINGS
        // =========================

        navSettings.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ProfileActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
            finish();
        });

        // =========================
        // PROFILE
        // =========================

        navProfile.setOnClickListener(v -> {
            // Already on Profile
        });
    }

    // =====================================================
    // LOAD USERNAME
    // =====================================================

    private void loadUsername() {

        String username = appPreferences.getString(
                "username",
                ""
        );

        if (!username.isEmpty()) {
            tvProfileName.setText(username);
        } else {
            tvProfileName.setText("Username");
        }
    }

    // =====================================================
    // LOAD SIMULATOR SCORES
    // =====================================================

    private void loadSimulatorScores() {

        // Traffic Light
        displayScore(
                tvTrafficLightScore,
                "Traffic Light"
        );

        // Pedestrian Crossing
        displayScore(
                tvPedestrianScore,
                "Pedestrian Crossing"
        );

        // Right of Way
        displayScore(
                tvRightOfWayScore,
                "Right of Way"
        );

        // Lane Use
        displayScore(
                tvLaneUseScore,
                "Lane Use"
        );
    }

    // =====================================================
    // DISPLAY ONE SIMULATOR SCORE
    // =====================================================

    private void displayScore(
            TextView textView,
            String scenario
    ) {

        int attempts = simulatorPreferences.getInt(
                "attempts_" + scenario,
                0
        );

        int bestScore = simulatorPreferences.getInt(
                "best_" + scenario,
                0
        );

        if (attempts > 0) {

            textView.setText(
                    "Best Score: " +
                            bestScore +
                            "%  •  Attempts: " +
                            attempts
            );

        } else {

            textView.setText(
                    "No score yet"
            );
        }
    }

    // =====================================================
    // REFRESH WHEN RETURNING TO PROFILE
    // =====================================================

    @Override
    protected void onResume() {
        super.onResume();

        loadUsername();
        loadSimulatorScores();
    }
}