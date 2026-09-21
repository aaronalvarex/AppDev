package com.example.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsernameActivity extends AppCompatActivity {

    EditText editUsername;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        editUsername = findViewById(R.id.editUsername);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            String username = editUsername.getText().toString().trim();

            if (username.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please enter your username",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                SharedPreferences preferences =
                        getSharedPreferences("AppSettings", MODE_PRIVATE);

                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putString("username", username);
                editor.apply();

                Intent intent = new Intent(
                        UsernameActivity.this,
                        HomeActivity.class
                );

                startActivity(intent);
                finish();
            }
        });
    }
}