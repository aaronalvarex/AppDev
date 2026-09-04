package com.example.appdev;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

    // Category Chips
    private TextView chipAll, chipWarning, chipRegulatory, chipInformative, chipPriority, chipTemporary;
    private TextView[] allChips;

    // Cards Views
    private View cardWarning, cardRegulatory, cardInformative, cardPriority, cardTemporary;

    // Data Structure for filtering
    private final List<SignItem> signList = new ArrayList<>();
    private String selectedCategory = "All";

    private static class SignItem {
        View cardView;
        String title;
        String description;
        String category;

        SignItem(View cardView, String title, String description, String category) {
            this.cardView = cardView;
            this.title = title;
            this.description = description;
            this.category = category;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_signs);

        initViews();
        setupData();
        setupCategoryListeners();
        setupSearchListener();
        setupNavigation();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        etSearch = findViewById(R.id.etSearch);

        // Chips
        chipAll = findViewById(R.id.chipAll);
        chipWarning = findViewById(R.id.chipWarning);
        chipRegulatory = findViewById(R.id.chipRegulatory);
        chipInformative = findViewById(R.id.chipInformative);
        chipPriority = findViewById(R.id.chipPriority);
        chipTemporary = findViewById(R.id.chipTemporary);

        allChips = new TextView[]{chipAll, chipWarning, chipRegulatory, chipInformative, chipPriority, chipTemporary};

        // Cards
        cardWarning = findViewById(R.id.cardWarningSigns);
        cardRegulatory = findViewById(R.id.cardRegulatorySigns);
        cardInformative = findViewById(R.id.cardInformativeSigns);
        cardPriority = findViewById(R.id.cardPrioritySigns);
        cardTemporary = findViewById(R.id.cardTemporarySigns);

        // Bottom Nav
        navHome = findViewById(R.id.navHome);
        navSettings = findViewById(R.id.navSettings);
        navProfile = findViewById(R.id.navProfile);
    }

    private void setupData() {
        // Setup Card Data & Store in List
        signList.add(new SignItem(cardWarning, "Warning Signs", "Yellow diamond-shaped signs that warn of potential hazards.", "Warning"));
        signList.add(new SignItem(cardRegulatory, "Regulatory Signs", "Red and White signs that show legal restrictions.", "Regulatory"));
        signList.add(new SignItem(cardInformative, "Informative Signs", "Blue signs that provide guidance and information.", "Informative"));
        signList.add(new SignItem(cardPriority, "Priority Signs", "Signs that indicate right-of-way and priority.", "Priority"));
        signList.add(new SignItem(cardTemporary, "Temporary Signs", "Signs used in roadwork and special situations.", "Temporary"));

        // Populate Views
        setCardData(cardWarning, R.drawable.ic_warning_sign, "Warning Signs", "Yellow diamond-shaped signs that warn of potential hazards.");
        setCardData(cardRegulatory, R.drawable.ic_regulatory_sign, "Regulatory Signs", "Red and White signs that show legal restrictions.");
        setCardData(cardInformative, R.drawable.ic_informative_sign, "Informative Signs", "Blue signs that provide guidance and information.");
        setCardData(cardPriority, R.drawable.ic_priority_sign, "Priority Signs", "Signs that indicate right-of-way and priority.");
        setCardData(cardTemporary, R.drawable.ic_temporary_sign, "Temporary Signs", "Signs used in roadwork and special situations.");
    }

    private void setCardData(View cardView, int imageResId, String title, String description) {
        ImageView img = cardView.findViewById(R.id.imgSign);
        TextView tvTitle = cardView.findViewById(R.id.tvSignTitle);
        TextView tvDesc = cardView.findViewById(R.id.tvSignDescription);

        img.setImageResource(imageResId);
        tvTitle.setText(title);
        tvDesc.setText(description);

        cardView.setOnClickListener(v ->
                Toast.makeText(RoadSignsActivity.this, "Selected: " + title, Toast.LENGTH_SHORT).show()
        );
    }

    private void setupCategoryListeners() {
        chipAll.setOnClickListener(v -> selectCategory("All", chipAll));
        chipWarning.setOnClickListener(v -> selectCategory("Warning", chipWarning));
        chipRegulatory.setOnClickListener(v -> selectCategory("Regulatory", chipRegulatory));
        chipInformative.setOnClickListener(v -> selectCategory("Informative", chipInformative));
        chipPriority.setOnClickListener(v -> selectCategory("Priority", chipPriority));
        chipTemporary.setOnClickListener(v -> selectCategory("Temporary", chipTemporary));
    }

    private void selectCategory(String category, TextView selectedChip) {
        selectedCategory = category;

        // Reset all chips style
        for (TextView chip : allChips) {
            chip.setBackgroundResource(R.drawable.bg_chip_inactive);
            chip.setTextColor(Color.parseColor("#0052CC"));
        }

        // Highlight selected chip
        selectedChip.setBackgroundResource(R.drawable.bg_chip_active);
        selectedChip.setTextColor(Color.WHITE);

        filterSigns();
    }

    private void setupSearchListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSigns();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterSigns() {
        String query = etSearch.getText().toString().trim().toLowerCase();

        for (SignItem item : signList) {
            boolean matchesCategory = selectedCategory.equals("All") || item.category.equalsIgnoreCase(selectedCategory);
            boolean matchesQuery = item.title.toLowerCase().contains(query) || item.description.toLowerCase().contains(query);

            if (matchesCategory && matchesQuery) {
                item.cardView.setVisibility(View.VISIBLE);
            } else {
                item.cardView.setVisibility(View.GONE);
            }
        }
    }

    private void setupNavigation() {
        btnBack.setOnClickListener(v -> finish());

        navHome.setOnClickListener(v -> finish());

        navSettings.setOnClickListener(v -> {
            startActivity(new Intent(RoadSignsActivity.this, SettingsActivity.class));
            finish();
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(RoadSignsActivity.this, ProfileActivity.class));
            finish();
        });
    }
}