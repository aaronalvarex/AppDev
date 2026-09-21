package com.example.appdev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editUsername;
    private Button btnSave;
    private TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editUsername = findViewById(R.id.editUsername);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        // Get saved username
        SharedPreferences preferences =
                getSharedPreferences("AppSettings", MODE_PRIVATE);

        String username =
                preferences.getString("username", "");

        editUsername.setText(username);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Save username
        btnSave.setOnClickListener(v -> {

            String newUsername =
                    editUsername.getText().toString().trim();

            if (newUsername.isEmpty()) {

                Toast.makeText(
                        EditProfileActivity.this,
                        "Please enter a username",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                preferences.edit()
                        .putString("username", newUsername)
                        .apply();

                Toast.makeText(
                        EditProfileActivity.this,
                        "Profile updated!",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });
    }
}