package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class TrafficRulesActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout navHome;
    private LinearLayout navSettings;
    private LinearLayout navProfile;

    private LinearLayout btnTurningOvertaking;
    private LinearLayout btnChangingLanes;
    private LinearLayout btnRightOfWay;
    private LinearLayout btnYellowBox;
    private LinearLayout btnEmergency;
    private LinearLayout btnParking;
    private LinearLayout btnHitching;
    private LinearLayout btnRedFlagLights;
    private LinearLayout btnUseRedFlags;
    private LinearLayout btnMufflers;
    private LinearLayout btnTires;
    private LinearLayout btnRoadCrash;
    private LinearLayout btnOtherDrivingRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_traffic_rules);

        // BACK
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // BOTTOM NAVIGATION
        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);

        // HOME
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        TrafficRulesActivity.this,
                        HomeActivity.class
                );

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // SETTINGS
        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        SettingsActivity.class
                ));
                finish();
            }
        });

        // PROFILE
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        ProfileActivity.class
                ));
                finish();
            }
        });

        // FIND ALL CARDS
        btnTurningOvertaking = findViewById(R.id.btnTurningOvertaking);
        btnChangingLanes = findViewById(R.id.btnChangingLanes);
        btnRightOfWay = findViewById(R.id.btnRightOfWay);
        btnYellowBox = findViewById(R.id.btnYellowBox);
        btnEmergency = findViewById(R.id.btnEmergency);
        btnParking = findViewById(R.id.btnParking);
        btnHitching = findViewById(R.id.btnHitching);
        btnRedFlagLights = findViewById(R.id.btnRedFlagLights);
        btnUseRedFlags = findViewById(R.id.btnUseRedFlags);
        btnMufflers = findViewById(R.id.btnMufflers);
        btnTires = findViewById(R.id.btnTires);
        btnRoadCrash = findViewById(R.id.btnRoadCrash);
        btnOtherDrivingRules = findViewById(R.id.btnOtherDrivingRules);


        // 01 TURNING AND OVERTAKING
        btnTurningOvertaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        TurningOvertakingActivity.class
                ));
            }
        });


        // 02 CHANGING LANES
        btnChangingLanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        ChangingLanesActivity.class
                ));
            }
        });


        // 03 RIGHT OF WAY
        btnRightOfWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        RightOfWayActivity.class
                ));
            }
        });


        // 04 YELLOW BOX
        btnYellowBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        YellowBoxActivity.class
                ));
            }
        });


        // 05 EMERGENCY VEHICLE
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        EmergencyVehicleActivity.class
                ));
            }
        });


        // 06 PARKING
        btnParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        ParkingActivity.class
                ));
            }
        });


        // 07 HITCHING AND DRIVING AGAINST TRAFFIC
        btnHitching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        HitchingActivity.class
                ));
            }
        });


        // 08 RED FLAG LIGHTS
        btnRedFlagLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        RedFlagLightsActivity.class
                ));
            }
        });


        // 09 USE OF RED FLAGS
        btnUseRedFlags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        UseRedFlagsActivity.class
                ));
            }
        });


        // 10 MUFFLERS
        btnMufflers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        MufflersActivity.class
                ));
            }
        });


        // 11 TIRES
        btnTires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        TiresActivity.class
                ));
            }
        });


        // 12 ROAD CRASH
        btnRoadCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        RoadCrashActivity.class
                ));
            }
        });


        // 13 OTHER DRIVING RULES
        btnOtherDrivingRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        TrafficRulesActivity.this,
                        OtherDrivingRulesActivity.class
                ));
            }
        });
    }
}