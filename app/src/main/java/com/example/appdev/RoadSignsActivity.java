package com.example.appdev;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RoadSignsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText etSearch;

    private LinearLayout navHome, navSettings, navProfile;


    // CATEGORY CHIPS //
    private TextView chipWarning;
    private TextView chipRegulatory;
    private TextView chipPedestrian;
    private TextView chipRoadObstacle;

    private TextView[] allChips;


    // CATEGORY CARDS //

    private View cardWarning;
    private View cardRegulatory;
    private View cardPedestrian;
    private View cardRoadObstacle;


    // SIGN GRIDS //

    private GridLayout warningSignsGrid;
    private GridLayout regulatorySignsGrid;
    private GridLayout pedestrianSignsGrid;
    private GridLayout roadObstacleSignsGrid;


    // DETAIL POPUP //

    private FrameLayout detailOverlay;
    private ImageView detailImage;
    private TextView detailTitle;
    private TextView detailDescription;
    private TextView btnCloseDetail;


    // DATA FOR FILTERING //

    private final List<SignItem> signList = new ArrayList<>();

    private String selectedCategory = "All";


    // SIGN ITEM CLASS //

    private static class SignItem {

        View cardView;
        String title;
        String description;
        String category;

        SignItem(
                View cardView,
                String title,
                String description,
                String category
        ) {
            this.cardView = cardView;
            this.title = title;
            this.description = description;
            this.category = category;
        }
    }


    // ON CREATE //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_road_signs);

        initViews();

        setupData();

        setupCategoryListeners();

        setupSearchListener();

        setupNavigation();

        selectCategory("Warning", chipWarning);

        showWarningSigns();
        showRegulatorySigns();
        showPedestrianSigns();
        showRoadObstacleSigns();

    // Return to Warning as the default screen
        selectCategory("Warning", chipWarning);
        showWarningSigns();
    }

    // INITIALIZE VIEWS //

    private void initViews() {

        btnBack = findViewById(R.id.btnBack);

        etSearch = findViewById(R.id.etSearch);


        // CHIPS //

        chipWarning = findViewById(R.id.chipWarning);

        chipRegulatory = findViewById(R.id.chipRegulatory);

        chipPedestrian = findViewById(R.id.chipPedestrian);

        chipRoadObstacle = findViewById(R.id.chipRoadObstacle);


        allChips = new TextView[]{
                chipWarning,
                chipRegulatory,
                chipPedestrian,
                chipRoadObstacle
        };


        // CATEGORY CARDS //

        cardWarning =
                findViewById(R.id.cardWarningSigns);

        cardRegulatory =
                findViewById(R.id.cardRegulatorySigns);

        cardPedestrian =
                findViewById(R.id.cardPedestrianSigns);

        cardRoadObstacle =
                findViewById(R.id.cardRoadObstacleSigns);


        // WARNING GRID //

        warningSignsGrid =
                findViewById(R.id.warningSignsGrid);


        // REGULATORY GRID //

        regulatorySignsGrid =
                findViewById(R.id.regulatorySignsGrid);

        // PEDESTRIAN GRID //
        pedestrianSignsGrid =
                findViewById(R.id.pedestrianSignsGrid);

        // ROAD OBSTACLE GRID //

        roadObstacleSignsGrid =
                findViewById(R.id.roadObstacleSignsGrid);

        // DETAIL POPUP //

        detailOverlay =
                findViewById(R.id.detailOverlay);
        detailImage =
                findViewById(R.id.detailImage);
        detailTitle =
                findViewById(R.id.detailTitle);
        detailDescription =
                findViewById(R.id.detailDescription);
        btnCloseDetail =
                findViewById(R.id.btnCloseDetail);


        btnCloseDetail.setOnClickListener(v ->
                detailOverlay.setVisibility(View.GONE)
        );

        // BOTTOM NAVIGATION //

        navHome =
                findViewById(R.id.navHome);

        navSettings =
                findViewById(R.id.navSettings);

        navProfile =
                findViewById(R.id.navProfile);
    }

    // SETUP CATEGORY DATA //

    private void setupData() {

        signList.add(new SignItem(
                cardWarning,
                "Warning Signs",
                "Yellow diamond-shaped signs that warn of potential hazards.",
                "Warning"
        ));


        signList.add(new SignItem(
                cardRegulatory,
                "Regulatory Signs",
                "Red and White signs that show legal restrictions.",
                "Regulatory"
        ));


        signList.add(new SignItem(
                cardPedestrian,
                "Pedestrian Signs",
                "Blue signs that provide guidance and information.",
                "Pedestrian"
        ));


        signList.add(new SignItem(
                cardRoadObstacle,
                "Road Obstacle Signs",
                "Signs that indicate right-of-way and priority.",
                "Road Obstacle"
        ));

        // WARNING CARD //

        setCardData(
                cardWarning,
                R.drawable.ic_warning_sign,
                "Warning Signs",
                "Yellow diamond-shaped signs that warn of potential hazards."
        );

        // REGULATORY CARD //

        setCardData(
                cardRegulatory,
                R.drawable.ic_regulatory_sign,
                "Regulatory Signs",
                "Red and White signs that show legal restrictions."
        );

        // PEDESTRIAN CARD //

        setCardData(
                cardPedestrian,
                R.drawable.ic_informative_sign,
                "Pedestrian Signs",
                "Blue signs that provide guidance and information."
        );

        // ROAD OBSTACLE CARD //

        setCardData(
                cardRoadObstacle,
                R.drawable.ic_priority_sign,
                "Road Obstacle Signs",
                "Signs that indicate right-of-way and priority."
        );
    }

    // SET CARD DATA //

    private void setCardData(
            View cardView,
            int imageResId,
            String title,
            String description
    ) {

        ImageView img =
                cardView.findViewById(R.id.imgSign);

        TextView tvTitle =
                cardView.findViewById(R.id.tvSignTitle);

        TextView tvDesc =
                cardView.findViewById(R.id.tvSignDescription);


        img.setImageResource(imageResId);

        tvTitle.setText(title);

        tvDesc.setText(description);


        cardView.setOnClickListener(v -> {

            if (title.equals("Warning Signs")) {

                selectCategory(
                        "Warning",
                        chipWarning
                );

                showWarningSigns();


            } else if (title.equals("Regulatory Signs")) {

                selectCategory(
                        "Regulatory",
                        chipRegulatory
                );

                showRegulatorySigns();


            } else if (title.equals("Pedestrian Signs")) {

                selectCategory(
                        "Pedestrian",
                        chipPedestrian
                );

                showPedestrianSigns();


            } else if (title.equals("Road Obstacle Signs")) {

                selectCategory(
                        "Road Obstacle",
                        chipRoadObstacle
                );

                showRoadObstacleSigns();


            } else {

                Toast.makeText(
                        RoadSignsActivity.this,
                        "Selected: " + title,
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
    }

    // CATEGORY LISTENERS //

    private void setupCategoryListeners() {

        // WARNING //

        chipWarning.setOnClickListener(v -> {

            selectCategory(
                    "Warning",
                    chipWarning
            );

            showWarningSigns();
        });


        // =========================
        // REGULATORY
        // =========================

        chipRegulatory.setOnClickListener(v -> {

            selectCategory(
                    "Regulatory",
                    chipRegulatory
            );

            showRegulatorySigns();
        });


        // =========================
        // PEDESTRIAN
        // =========================

        chipPedestrian.setOnClickListener(v -> {

            selectCategory(
                    "Pedestrian",
                    chipPedestrian
            );

            showPedestrianSigns();
        });


        // =========================
        // ROAD OBSTACLE
        // =========================

        chipRoadObstacle.setOnClickListener(v -> {

            selectCategory(
                    "Road Obstacle",
                    chipRoadObstacle
            );

            showRoadObstacleSigns();
        });
    }


    // =========================
    // SHOW CATEGORY CARDS
    // =========================

    private void showCategoryCards() {

        cardWarning.setVisibility(View.VISIBLE);

        cardRegulatory.setVisibility(View.VISIBLE);

        cardPedestrian.setVisibility(View.VISIBLE);

        cardRoadObstacle.setVisibility(View.VISIBLE);


        warningSignsGrid.setVisibility(View.GONE);

        regulatorySignsGrid.setVisibility(View.GONE);

        pedestrianSignsGrid.setVisibility(View.GONE);

        roadObstacleSignsGrid.setVisibility(View.GONE);
    }


    // ==================================================
    // WARNING SIGNS
    // ==================================================

    private void showWarningSigns() {

        cardWarning.setVisibility(View.GONE);

        cardRegulatory.setVisibility(View.GONE);

        cardPedestrian.setVisibility(View.GONE);

        cardRoadObstacle.setVisibility(View.GONE);


        warningSignsGrid.setVisibility(View.VISIBLE);
        regulatorySignsGrid.setVisibility(View.GONE);
        pedestrianSignsGrid.setVisibility(View.GONE);
        roadObstacleSignsGrid.setVisibility(View.GONE);


        warningSignsGrid.removeAllViews();


        // 1
        addWarningSign(
                R.drawable.warning_sharp_turn,
                "Sharp Turn",
                "Indicates a sharp change in the direction of the roadway ahead."
        );


        // 2
        addWarningSign(
                R.drawable.warning_double_sharp_turn,
                "Double Sharp Turn",
                "Indicates two consecutive sharp turns ahead."
        );


        // 3
        addWarningSign(
                R.drawable.warning_curve,
                "Curve",
                "Indicates a curved section of the roadway ahead."
        );


        // 4
        addWarningSign(
                R.drawable.warning_t_junction,
                "T-Junction Ahead",
                "Indicates that the road terminates at a T-junction ahead."
        );


        // 5
        addWarningSign(
                R.drawable.warning_y_junction,
                "Y-Junction Ahead",
                "Indicates a Y-shaped junction where the roadway divides ahead."
        );


        // 6
        addWarningSign(
                R.drawable.warning_intersection_ahead,
                "Intersection Ahead",
                "Indicates an intersection ahead where road users should exercise caution."
        );


        // 7
        addWarningSign(
                R.drawable.warning_roundabout_ahead,
                "Roundabout Ahead",
                "Indicates that a roundabout is located ahead."
        );


        // 8
        addWarningSign(
                R.drawable.warning_side_road_junction_ahead,
                "Side Road Junction Ahead",
                "Indicates that a side road intersects with the main road ahead."
        );


        // 9
        addWarningSign(
                R.drawable.warning_approach_to_intersection_side_road,
                "Approach to Intersection – Side Road",
                "Indicates that a side road intersects with the main roadway ahead."
        );


        // 10
        addWarningSign(
                R.drawable.warning_approach_intersection_merging_traffic,
                "Approach to Intersection – Merging Traffic",
                "Indicates that traffic from another roadway will merge with the main traffic flow ahead."
        );


        // 11
        addWarningSign(
                R.drawable.warning_traffic_lights_ahead,
                "Traffic Lights Ahead",
                "Indicates the presence of traffic lights ahead."
        );


        // 12
        addWarningSign(
                R.drawable.warning_stop_sign_ahead,
                "Stop Sign Ahead",
                "Indicates that a stop sign is located ahead and requires the vehicle to stop."
        );


        // 13
        addWarningSign(
                R.drawable.warning_give_way_sign_ahead,
                "Give Way Sign Ahead",
                "Indicates that drivers must give way to other road users at the upcoming location."
        );


        // 14
        addWarningSign(
                R.drawable.warning_road_narrows_ahead,
                "Road Narrows Ahead",
                "Indicates that the roadway becomes narrower ahead."
        );


        // 15
        addWarningSign(
                R.drawable.warning_narrow_bridge_ahead,
                "Narrow Bridge Ahead",
                "Indicates that a narrow bridge is located ahead."
        );


        // 16
        addWarningSign(
                R.drawable.warning_divided_road_ahead,
                "Divided Road Ahead",
                "Indicates that the roadway ahead is divided into separate traffic directions."
        );


        // 17
        addWarningSign(
                R.drawable.warning_end_of_divided_road_ahead,
                "End of Divided Road Ahead",
                "Indicates that the divided roadway ends and traffic will proceed on a single roadway."
        );
    }


    // ==================================================
    // ADD WARNING SIGN CARD
    // ==================================================

    private void addWarningSign(
            int imageResId,
            String title,
            String description
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                android.view.Gravity.CENTER
        );

        card.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );


        // =========================
        // ROUNDED BACKGROUND
        // =========================

        android.graphics.drawable.GradientDrawable background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(
                Color.WHITE
        );

        background.setStroke(
                dp(2),
                Color.rgb(50, 105, 210)
        );

        background.setCornerRadius(
                dp(16)
        );

        card.setBackground(
                background
        );

        card.setClickable(true);


        // =========================
        // IMAGE
        // =========================

        ImageView image =
                new ImageView(this);

        image.setImageResource(
                imageResId
        );

        image.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );


        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(150)
                );


        imageParams.setMargins(
                dp(5),
                dp(5),
                dp(5),
                dp(5)
        );


        card.addView(
                image,
                imageParams
        );


        // =========================
        // TITLE
        // =========================

        TextView titleText =
                new TextView(this);

        titleText.setText(
                title
        );

        titleText.setTextColor(
                Color.rgb(50, 105, 210)
        );

        titleText.setTextSize(16);

        titleText.setGravity(
                android.view.Gravity.CENTER
        );

        titleText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleText.setMinLines(2);

        titleText.setPadding(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );


        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );


        card.addView(
                titleText,
                titleParams
        );


        // =========================
        // CLICK
        // =========================

        card.setOnClickListener(v ->
                showSignDetail(
                        imageResId,
                        title,
                        description
                )
        );


        // =========================
        // CARD SIZE
        // =========================

        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();

        params.width = 0;

        params.height = dp(230);


        params.columnSpec =
                GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                );


        params.setMargins(
                dp(6),
                dp(6),
                dp(6),
                dp(6)
        );


        warningSignsGrid.addView(
                card,
                params
        );
    }


    // ==================================================
    // REGULATORY SIGNS
    // ==================================================

    private void showRegulatorySigns() {

        cardWarning.setVisibility(View.GONE);

        cardRegulatory.setVisibility(View.GONE);

        cardPedestrian.setVisibility(View.GONE);

        cardRoadObstacle.setVisibility(View.GONE);


        warningSignsGrid.setVisibility(View.GONE);
        regulatorySignsGrid.setVisibility(View.VISIBLE);
        pedestrianSignsGrid.setVisibility(View.GONE);
        roadObstacleSignsGrid.setVisibility(View.GONE);

        regulatorySignsGrid.removeAllViews();


        // 1
        addRegulatorySign(
                R.drawable.regulatory_no_entry_all_vehicles,
                "No Entry for All Types of Vehicles",
                "Prohibits all types of vehicles from entering the designated area."
        );


        // 2
        addRegulatorySign(
                R.drawable.regulatory_no_bicycles,
                "No Bicycles",
                "Prohibits bicycles from entering or using the designated roadway."
        );


        // 3
        addRegulatorySign(
                R.drawable.regulatory_no_motorcycles,
                "No Motorcycles",
                "Prohibits motorcycles from entering or using the designated roadway."
        );


        // 4
        addRegulatorySign(
                R.drawable.regulatory_no_cars,
                "No Cars",
                "Prohibits cars from entering or using the designated roadway."
        );


        // 5
        addRegulatorySign(
                R.drawable.regulatory_no_buses,
                "No Buses",
                "Prohibits buses from entering or using the designated roadway."
        );


        // 6
        addRegulatorySign(
                R.drawable.regulatory_no_trucks,
                "No Trucks",
                "Prohibits trucks from entering or using the designated roadway."
        );


        // 7
        addRegulatorySign(
                R.drawable.regulatory_no_pedestrian_crossing,
                "No Pedestrian Crossing",
                "Prohibits pedestrians from crossing at the designated location."
        );


        // 8
        addRegulatorySign(
                R.drawable.regulatory_use_overpass,
                "Use Overpass",
                "Directs pedestrians to use the designated overpass for crossing the roadway."
        );


        // 9
        addRegulatorySign(
                R.drawable.regulatory_use_pedestrian_crossing,
                "Use Pedestrian Crossing",
                "Directs pedestrians to cross only at the designated pedestrian crossing."
        );


        // 10
        addRegulatorySign(
                R.drawable.regulatory_no_right_turn,
                "No Right Turn",
                "Prohibits vehicles from making a right turn at the designated location."
        );


        // 11
        addRegulatorySign(
                R.drawable.regulatory_no_left_turn,
                "No Left Turn",
                "Prohibits vehicles from making a left turn at the designated location."
        );


        // 12
        addRegulatorySign(
                R.drawable.regulatory_no_u_turn,
                "No U-Turn",
                "Prohibits vehicles from making a U-turn at the designated location."
        );


        // 13
        addRegulatorySign(
                R.drawable.regulatory_no_overtaking_zone,
                "No Overtaking Zone",
                "Prohibits vehicles from overtaking other vehicles within the designated area."
        );
    }


    // ==================================================
    // ADD REGULATORY SIGN CARD
    // ==================================================

    private void addRegulatorySign(
            int imageResId,
            String title,
            String description
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                android.view.Gravity.CENTER
        );

        card.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );


        // =========================
        // ROUNDED BACKGROUND
        // =========================

        android.graphics.drawable.GradientDrawable background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(
                Color.WHITE
        );

        background.setStroke(
                dp(2),
                Color.rgb(50, 105, 210)
        );

        background.setCornerRadius(
                dp(16)
        );

        card.setBackground(
                background
        );

        card.setClickable(true);


        // =========================
        // IMAGE
        // =========================

        ImageView image =
                new ImageView(this);

        image.setImageResource(
                imageResId
        );

        image.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );


        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(150)
                );


        imageParams.setMargins(
                dp(5),
                dp(5),
                dp(5),
                dp(5)
        );


        card.addView(
                image,
                imageParams
        );


        // =========================
        // TITLE
        // =========================

        TextView titleText =
                new TextView(this);

        titleText.setText(
                title
        );

        titleText.setTextColor(
                Color.rgb(50, 105, 210)
        );

        titleText.setTextSize(16);

        titleText.setGravity(
                android.view.Gravity.CENTER
        );

        titleText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleText.setMinLines(2);

        titleText.setPadding(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );


        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );


        card.addView(
                titleText,
                titleParams
        );


        // =========================
        // CLICK CARD
        // =========================

        card.setOnClickListener(v ->
                showSignDetail(
                        imageResId,
                        title,
                        description
                )
        );


        // =========================
        // CARD SIZE
        // =========================

        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();

        params.width = 0;

        params.height = dp(230);


        params.columnSpec =
                GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                );


        params.setMargins(
                dp(6),
                dp(6),
                dp(6),
                dp(6)
        );


        regulatorySignsGrid.addView(
                card,
                params
        );
    }

    // ==================================================
// PEDESTRIAN SIGNS
// ==================================================

    private void showPedestrianSigns() {

        cardWarning.setVisibility(View.GONE);

        cardRegulatory.setVisibility(View.GONE);

        cardPedestrian.setVisibility(View.GONE);

        cardRoadObstacle.setVisibility(View.GONE);


        warningSignsGrid.setVisibility(View.GONE);
        regulatorySignsGrid.setVisibility(View.GONE);
        pedestrianSignsGrid.setVisibility(View.VISIBLE);
        roadObstacleSignsGrid.setVisibility(View.GONE);


        pedestrianSignsGrid.removeAllViews();


        // 1
        addPedestrianSign(
                R.drawable.pedestrian_crossing_ahead,
                "Pedestrian Crossing Ahead",
                "Indicates a designated pedestrian crossing ahead."
        );


        // 2
        addPedestrianSign(
                R.drawable.pedestrian_children_crossing_ahead,
                "Children Crossing Ahead",
                "Indicates an area where children may cross the roadway."
        );


        // 3
        addPedestrianSign(
                R.drawable.pedestrian_bike_lane_ahead,
                "Bike Lane Ahead",
                "Indicates the presence of a designated bicycle lane ahead."
        );


        // 4
        addPedestrianSign(
                R.drawable.pedestrian_pwd_crossing_ahead,
                "Persons with Disabilities (PWDs) Crossing Ahead",
                "Indicates an area where persons with disabilities may cross the roadway ahead."
        );


        // 5
        addPedestrianSign(
                R.drawable.pedestrian_advising_pedestrians_cross_designated_areas,
                "Advising Pedestrians to Cross at Designated Areas",
                "Advises pedestrians to use designated crossing areas when crossing the roadway."
        );
    }

// ==================================================
// ADD PEDESTRIAN SIGN CARD
// ==================================================

    private void addPedestrianSign(
            int imageResId,
            String title,
            String description
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                android.view.Gravity.CENTER
        );

        card.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );


        // =========================
        // ROUNDED BACKGROUND
        // =========================

        android.graphics.drawable.GradientDrawable background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(
                Color.WHITE
        );

        background.setStroke(
                dp(2),
                Color.rgb(50, 105, 210)
        );

        background.setCornerRadius(
                dp(16)
        );

        card.setBackground(
                background
        );

        card.setClickable(true);

        // IMAGE //

        ImageView image =
                new ImageView(this);

        image.setImageResource(
                imageResId
        );

        image.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );


        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(150)
                );


        imageParams.setMargins(
                dp(5),
                dp(5),
                dp(5),
                dp(5)
        );


        card.addView(
                image,
                imageParams
        );

        // TITLE //

        TextView titleText =
                new TextView(this);

        titleText.setText(
                title
        );

        titleText.setTextColor(
                Color.rgb(50, 105, 210)
        );

        titleText.setTextSize(16);

        titleText.setGravity(
                android.view.Gravity.CENTER
        );

        titleText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleText.setMinLines(2);

        titleText.setPadding(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );


        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );


        card.addView(
                titleText,
                titleParams
        );


        // CLICK CARD //

        card.setOnClickListener(v ->
                showSignDetail(
                        imageResId,
                        title,
                        description
                )
        );


        // CARD SIZE //

        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();

        params.width = 0;

        params.height = dp(230);


        params.columnSpec =
                GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                );


        params.setMargins(
                dp(6),
                dp(6),
                dp(6),
                dp(6)
        );


        pedestrianSignsGrid.addView(
                card,
                params
        );
    }

    // ROAD OBSTACLE SIGNS //

    private void showRoadObstacleSigns() {

        cardWarning.setVisibility(View.GONE);

        cardRegulatory.setVisibility(View.GONE);

        cardPedestrian.setVisibility(View.GONE);

        cardRoadObstacle.setVisibility(View.GONE);


        warningSignsGrid.setVisibility(View.GONE);
        regulatorySignsGrid.setVisibility(View.GONE);
        pedestrianSignsGrid.setVisibility(View.GONE);
        roadObstacleSignsGrid.setVisibility(View.VISIBLE);


        roadObstacleSignsGrid.removeAllViews();


        // 1
        addRoadObstacleSign(
                R.drawable.road_obstacle_opening_bridge_ahead,
                "Opening Bridge Ahead",
                "Indicates the presence of an opening bridge ahead."
        );


        // 2
        addRoadObstacleSign(
                R.drawable.road_obstacle_uneven_road,
                "Uneven Road",
                "Indicates an uneven or irregular road surface ahead."
        );


        // 3
        addRoadObstacleSign(
                R.drawable.road_obstacle_hump_ahead,
                "Hump Ahead",
                "Indicates the presence of a road hump ahead."
        );


        // 4
        addRoadObstacleSign(
                R.drawable.road_obstacle_downhill_sign,
                "Downhill Sign",
                "Indicates a steep downward slope on the roadway ahead."
        );


        // 5
        addRoadObstacleSign(
                R.drawable.road_obstacle_uphill_sign,
                "Uphill Sign",
                "Indicates a steep upward slope on the roadway ahead."
        );


        // 6
        addRoadObstacleSign(
                R.drawable.road_obstacle_low_flying_airplane_zone,
                "Low-Flying Airplane Zone",
                "Indicates an area where low-flying aircraft may be present."
        );


        // 7
        addRoadObstacleSign(
                R.drawable.road_obstacle_slippery_road,
                "Slippery Road",
                "Indicates that the road surface may be slippery, particularly under wet conditions."
        );


        // 8
        addRoadObstacleSign(
                R.drawable.road_obstacle_spillway_ahead,
                "Spillway Ahead",
                "Indicates the presence of a spillway crossing ahead."
        );


        // 9
        addRoadObstacleSign(
                R.drawable.road_obstacle_flood_prone_area,
                "Flood-Prone Area",
                "Indicates an area where flooding may occur."
        );


        // 10
        addRoadObstacleSign(
                R.drawable.road_obstacle_landslide_prone_area,
                "Landslide-Prone Area",
                "Indicates an area susceptible to landslides."
        );


        // 11
        addRoadObstacleSign(
                R.drawable.road_obstacle_animal_crossing_ahead,
                "Animal Crossing Ahead",
                "Indicates that animals may cross the roadway ahead."
        );
    }

    // ROAD OBSTACLE SIGN CARD //

    private void addRoadObstacleSign(
            int imageResId,
            String title,
            String description
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                android.view.Gravity.CENTER
        );

        card.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );

        // ROUNDED BACKGROUND //

        android.graphics.drawable.GradientDrawable background =
                new android.graphics.drawable.GradientDrawable();

        background.setColor(
                Color.WHITE
        );

        background.setStroke(
                dp(2),
                Color.rgb(50, 105, 210)
        );

        background.setCornerRadius(
                dp(16)
        );

        card.setBackground(
                background
        );

        card.setClickable(true);

        // IMAGE //

        ImageView image =
                new ImageView(this);

        image.setImageResource(
                imageResId
        );

        image.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );


        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(150)
                );


        imageParams.setMargins(
                dp(5),
                dp(5),
                dp(5),
                dp(5)
        );


        card.addView(
                image,
                imageParams
        );


        // TITLE //

        TextView titleText =
                new TextView(this);

        titleText.setText(
                title
        );

        titleText.setTextColor(
                Color.rgb(50, 105, 210)
        );

        titleText.setTextSize(16);

        titleText.setGravity(
                android.view.Gravity.CENTER
        );

        titleText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleText.setMinLines(2);

        titleText.setPadding(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );


        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );


        card.addView(
                titleText,
                titleParams
        );

        // CLICK CARD //

        card.setOnClickListener(v ->
                showSignDetail(
                        imageResId,
                        title,
                        description
                )
        );

        // CARD SIZE //

        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();

        params.width = 0;

        params.height = dp(230);


        params.columnSpec =
                GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                );


        params.setMargins(
                dp(6),
                dp(6),
                dp(6),
                dp(6)
        );


        roadObstacleSignsGrid.addView(
                card,
                params
        );
    }

    // SHOW SIGN DETAIL //

    private void showSignDetail(
            int imageResId,
            String title,
            String description
    ) {

        detailImage.setImageResource(
                imageResId
        );

        detailTitle.setText(
                title
        );

        detailDescription.setText(
                description
        );

        detailOverlay.setVisibility(
                View.VISIBLE
        );
    }

    // SELECT CATEGORY //

    private void selectCategory(
            String category,
            TextView selectedChip
    ) {

        selectedCategory = category;


        // Reset all chips

        for (TextView chip : allChips) {

            chip.setBackgroundResource(
                    R.drawable.bg_chip_inactive
            );

            chip.setTextColor(
                    Color.parseColor("#0052CC")
            );
        }


        // Highlight selected chip

        selectedChip.setBackgroundResource(
                R.drawable.bg_chip_active
        );

        selectedChip.setTextColor(
                Color.WHITE
        );
    }

// SEARCH LISTENER //

    private void setupSearchListener() {

        etSearch.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after
                    ) {
                    }


                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count
                    ) {

                        searchSigns();
                    }


                    @Override
                    public void afterTextChanged(
                            Editable s
                    ) {
                    }
                }
        );
    }

    // SEARCH SIGNS //

    // ==================================================
// GLOBAL SEARCH
// ==================================================

    private void searchSigns() {

        String query = etSearch.getText()
                .toString()
                .trim()
                .toLowerCase();


        // ==============================================
        // IF SEARCH BAR IS EMPTY
        // SHOW CURRENT SELECTED CATEGORY
        // ==============================================

        if (query.isEmpty()) {

            if (selectedCategory.equals("Warning")) {

                showWarningSigns();

            } else if (selectedCategory.equals("Regulatory")) {

                showRegulatorySigns();

            } else if (selectedCategory.equals("Pedestrian")) {

                showPedestrianSigns();

            } else if (selectedCategory.equals("Road Obstacle")) {

                showRoadObstacleSigns();
            }

            return;
        }


        // ==============================================
        // SEARCH ALL CATEGORIES
        // ==============================================

        searchGrid(
                warningSignsGrid,
                query
        );

        searchGrid(
                regulatorySignsGrid,
                query
        );

        searchGrid(
                pedestrianSignsGrid,
                query
        );

        searchGrid(
                roadObstacleSignsGrid,
                query
        );


        // ==============================================
        // SHOW ONLY GRIDS THAT HAVE MATCHES
        // ==============================================

        boolean warningHasMatch =
                hasVisibleCards(warningSignsGrid);

        boolean regulatoryHasMatch =
                hasVisibleCards(regulatorySignsGrid);

        boolean pedestrianHasMatch =
                hasVisibleCards(pedestrianSignsGrid);

        boolean roadObstacleHasMatch =
                hasVisibleCards(roadObstacleSignsGrid);


        warningSignsGrid.setVisibility(
                warningHasMatch
                        ? View.VISIBLE
                        : View.GONE
        );

        regulatorySignsGrid.setVisibility(
                regulatoryHasMatch
                        ? View.VISIBLE
                        : View.GONE
        );

        pedestrianSignsGrid.setVisibility(
                pedestrianHasMatch
                        ? View.VISIBLE
                        : View.GONE
        );

        roadObstacleSignsGrid.setVisibility(
                roadObstacleHasMatch
                        ? View.VISIBLE
                        : View.GONE
        );



        // ==============================================
        // HIDE CATEGORY CARDS DURING SEARCH
        // ==============================================

        cardWarning.setVisibility(View.GONE);
        cardRegulatory.setVisibility(View.GONE);
        cardPedestrian.setVisibility(View.GONE);
        cardRoadObstacle.setVisibility(View.GONE);
    }

    // ==================================================
// SEARCH ONE GRID
// ==================================================

    private void searchGrid(
            GridLayout grid,
            String query
    ) {

        for (int i = 0; i < grid.getChildCount(); i++) {

            View card =
                    grid.getChildAt(i);

            TextView titleText = null;


            if (card instanceof LinearLayout) {

                LinearLayout layout =
                        (LinearLayout) card;


                for (int j = 0;
                     j < layout.getChildCount();
                     j++) {

                    View child =
                            layout.getChildAt(j);


                    if (child instanceof TextView) {

                        titleText =
                                (TextView) child;

                        break;
                    }
                }
            }


            if (titleText != null) {

                String title =
                        titleText.getText()
                                .toString()
                                .toLowerCase();


                if (title.contains(query)) {

                    card.setVisibility(
                            View.VISIBLE
                    );

                } else {

                    card.setVisibility(
                            View.GONE
                    );
                }
            }
        }
    }

    // ==================================================
    // CHECK IF GRID HAS VISIBLE CARDS
    // ==================================================

    private boolean hasVisibleCards(
            GridLayout grid
    ) {

        for (int i = 0;
             i < grid.getChildCount();
             i++) {

            View card =
                    grid.getChildAt(i);


            if (card.getVisibility()
                    == View.VISIBLE) {

                return true;
            }
        }


        return false;
    }

    // BOTTOM NAVIGATION //

    private void setupNavigation() {

        // BACK //

        btnBack.setOnClickListener(v ->
                finish()
        );


        // HOME //

        navHome.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            RoadSignsActivity.this,
                            HomeActivity.class
                    );

            startActivity(intent);

            finish();
        });


        // SETTINGS //

        navSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            RoadSignsActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);

            finish();
        });


        // PROFILE //

        navProfile.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            RoadSignsActivity.this,
                            ProfileActivity.class
                    );

            startActivity(intent);

            finish();
        });
    }


    // DP HELPER //

    private int dp(int value) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
                        + 0.5f
        );
    }
}