package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout navHome, navSettings, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toast.makeText(this, "SettingsActivity: onCreate()", Toast.LENGTH_SHORT).show();

        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // Pag pino-pindot ang Home tab, babalik sa Home
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Navigation Handler para sa Profile
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish(); // Isasara ang Settings para malinis ang activity stack
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "SettingsActivity: onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "SettingsActivity: onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "SettingsActivity: onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "SettingsActivity: onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "SettingsActivity: onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "SettingsActivity: onDestroy()", Toast.LENGTH_SHORT).show();
    }
}