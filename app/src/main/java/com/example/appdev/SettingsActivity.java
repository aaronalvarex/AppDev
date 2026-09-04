package com.example.appdev;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout navHome, navSettings, navProfile;
    private RelativeLayout btnLanguage, btnHowToUse, btnPrivacyPolicy, btnTerms, btnAbout, btnReportIssue;
    private TextView tvSelectedLanguage;

    // List ng English language options
    private final String[] languageOptions = {"English (US)", "English (UK)", "English (Philippines)"};
    private int selectedLanguageIndex = 0; // Default: English (US)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        // Navigation Views
        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // Settings Buttons
        btnLanguage = findViewById(R.id.btnLanguage);
        tvSelectedLanguage = findViewById(R.id.tvSelectedLanguage);

        btnHowToUse = findViewById(R.id.btnHowToUse);
        btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
        btnTerms = findViewById(R.id.btnTerms);
        btnAbout = findViewById(R.id.btnAbout);
        btnReportIssue = findViewById(R.id.btnReportIssue);
    }

    private void setupClickListeners() {
        // Language Picker Dialog Action
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });

        // Placeholder Toast Feedback para sa iba pang options
        btnHowToUse.setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "How to Use section", Toast.LENGTH_SHORT).show()
        );

        btnPrivacyPolicy.setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "Privacy Policy section", Toast.LENGTH_SHORT).show()
        );

        btnTerms.setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "Terms of Service section", Toast.LENGTH_SHORT).show()
        );

        btnAbout.setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "DriveSmart PH v1.0.0", Toast.LENGTH_SHORT).show()
        );

        btnReportIssue.setOnClickListener(v ->
                Toast.makeText(SettingsActivity.this, "Report an Issue section", Toast.LENGTH_SHORT).show()
        );

        // Bottom Navigation Actions
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Language");

        builder.setSingleChoiceItems(languageOptions, selectedLanguageIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedLanguageIndex = which;
                String selectedLang = languageOptions[which];

                // Update textview value
                tvSelectedLanguage.setText(selectedLang);

                Toast.makeText(SettingsActivity.this, "Language set to " + selectedLang, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}