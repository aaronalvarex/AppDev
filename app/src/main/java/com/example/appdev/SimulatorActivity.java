package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SimulatorActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout navHome, navSettings, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        // Populate dynamic data sa card items
        setupScenarioCards();

        // Back Button Handler (Babalik sa Home / isasara ang Simulator screen)
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Navigation setup
        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupScenarioCards() {
        // 1. Traffic Light
        View card1 = findViewById(R.id.cardTrafficLight);
        setCardData(card1, R.drawable.img_traffic_light, "Traffic Light", "Test your response at traffic lights.");

        // 2. Stop Sign
        View card2 = findViewById(R.id.cardStopSign);
        setCardData(card2, R.drawable.img_stop_sign, "Stop Sign", "Practice stopping at stop signs.");

        // 3. Pedestrian Crossing
        View card3 = findViewById(R.id.cardPedestrianCrossing);
        setCardData(card3, R.drawable.img_pedestrian_crossing, "Pedestrian Crossing", "Learn to yield for pedestrians.");

        // 4. Right-of-Way
        View card4 = findViewById(R.id.cardRightOfWay);
        setCardData(card4, R.drawable.img_right_of_way, "Right-of-Way", "Understand who goes first in intersections.");

        // 5. Lane Use
        View card5 = findViewById(R.id.cardLaneUse);
        setCardData(card5, R.drawable.img_lane_use, "Lane Use", "Choose the correct lane for safety.");
    }

    private void setCardData(View cardView, int imageResId, String title, String description) {
        ImageView img = cardView.findViewById(R.id.imgScenario);
        TextView tvTitle = cardView.findViewById(R.id.tvTitle);
        TextView tvDesc = cardView.findViewById(R.id.tvDescription);

        img.setImageResource(imageResId);
        tvTitle.setText(title);
        tvDesc.setText(description);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimulatorActivity.this, "Selected: " + title, Toast.LENGTH_SHORT).show();
            }
        });
    }
}