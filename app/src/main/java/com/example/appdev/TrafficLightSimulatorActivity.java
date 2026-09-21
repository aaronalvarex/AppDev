package com.example.appdev;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrafficLightSimulatorActivity extends AppCompatActivity {

    // =========================================================
    // TRAFFIC LIGHT VIEWS
    // =========================================================

    private View redLight;
    private View yellowLight;
    private View greenLight;

    private View car;
    private View stopLine;


    // =========================================================
    // TEXT VIEWS
    // =========================================================

    private TextView tvScenario;
    private TextView tvProgress;
    private TextView tvResult;
    private TextView tvScore;


    // =========================================================
    // BUTTONS
    // =========================================================

    private Button btnStop;
    private Button btnSlow;
    private Button btnGo;
    private Button btnCaution;
    private Button btnNext;


    // =========================================================
    // GAME VARIABLES
    // =========================================================

    private int scenarioNumber = 0;
    private int score = 0;

    private boolean answerSelected = false;

    private List<String[]> selectedQuestions = new ArrayList<>();


    // =========================================================
    // QUESTIONS
    // =========================================================

    private final String[][] questionBank = {

            // =================================================
            // RED SIGNAL
            // =================================================

            {
                    "You approach an intersection and the traffic light is RED. What should you do?",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "STOP",
                    "RED"
            },

            {
                    "The traffic light is RED and you are approaching the marked stop line. What should you do?",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "STOP",
                    "RED"
            },

            {
                    "You are driving toward a RED traffic signal. What is the proper action?",
                    "STOP",
                    "SLOW DOWN",
                    "GO WITH CAUTION",
                    "STOP",
                    "RED"
            },

            {
                    "A RED signal is displayed at the intersection ahead. What should you do?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "RED"
            },

            {
                    "You reach an intersection while the traffic light is RED. What should you do before proceeding?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "RED"
            },


            // =================================================
            // FLASHING RED
            // =================================================

            {
                    "You approach a FLASHING RED traffic signal. What should you do first?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "FLASHING_RED"
            },

            {
                    "The traffic signal is FLASHING RED. What is the proper action?",
                    "STOP",
                    "GO WITH CAUTION",
                    "GO",
                    "STOP",
                    "FLASHING_RED"
            },

            {
                    "You are approaching a FLASHING RED signal and the intersection is not yet safe. What should you do?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "FLASHING_RED"
            },

            {
                    "You have reached a FLASHING RED signal. What should you do before proceeding?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "FLASHING_RED"
            },

            {
                    "A FLASHING RED light is operating at the intersection. What should you do?",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "STOP",
                    "FLASHING_RED"
            },


            // =================================================
            // YELLOW SIGNAL
            // =================================================

            {
                    "The traffic light changes to YELLOW as you approach the intersection. What should you do?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "YELLOW"
            },

            {
                    "You see a YELLOW traffic signal while approaching the intersection. What is the safest action?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "YELLOW"
            },

            {
                    "The signal ahead is YELLOW and the RED signal is about to appear. What should you do?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "YELLOW"
            },

            {
                    "You are approaching an intersection and the signal turns YELLOW. What should you do?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "YELLOW"
            },

            {
                    "A YELLOW signal is displayed as you approach the intersection. What should you do?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "YELLOW"
            },


            // =================================================
            // FLASHING YELLOW
            // =================================================

            {
                    "You approach a FLASHING YELLOW traffic signal. What should you do?",
                    "SLOW DOWN",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "FLASHING_YELLOW"
            },

            {
                    "The traffic light is FLASHING YELLOW. What is the proper action?",
                    "SLOW DOWN",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "FLASHING_YELLOW"
            },

            {
                    "You see a FLASHING YELLOW signal at an intersection. What should you do?",
                    "SLOW DOWN",
                    "GO",
                    "STOP",
                    "SLOW DOWN",
                    "FLASHING_YELLOW"
            },

            {
                    "A FLASHING YELLOW signal is operating ahead. What should you do?",
                    "SLOW DOWN",
                    "GO",
                    "STOP",
                    "SLOW DOWN",
                    "FLASHING_YELLOW"
            },

            {
                    "You are approaching a FLASHING YELLOW traffic light. What is the safest action?",
                    "SLOW DOWN",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "FLASHING_YELLOW"
            },


            // =================================================
            // GREEN SIGNAL
            // =================================================

            {
                    "The traffic light is GREEN and the intersection is clear. What should you do?",
                    "GO",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "GREEN"
            },

            {
                    "The traffic signal is GREEN and there are no pedestrians crossing. What should you do?",
                    "GO",
                    "STOP",
                    "GO WITH CAUTION",
                    "GO",
                    "GREEN"
            },

            {
                    "You have a GREEN signal and the intersection is clear of conflicting traffic. What should you do?",
                    "GO",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "GREEN"
            },

            {
                    "The light is GREEN and it is safe to proceed through the intersection. What should you do?",
                    "GO",
                    "STOP",
                    "GO WITH CAUTION",
                    "GO",
                    "GREEN"
            },

            {
                    "You are facing a GREEN traffic signal and there are no pedestrians using the intersection. What should you do?",
                    "GO",
                    "STOP",
                    "SLOW DOWN",
                    "GO",
                    "GREEN"
            },


            // =================================================
            // GREEN + PEDESTRIANS
            // =================================================

            {
                    "The traffic light is GREEN, but pedestrians are lawfully crossing the intersection. What should you do?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "GREEN"
            },

            {
                    "You have a GREEN signal, but pedestrians are still crossing your path. What should you do?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "GREEN"
            },

            {
                    "The traffic light is GREEN, but pedestrians are using the intersection. What should you do?",
                    "STOP",
                    "GO",
                    "SLOW DOWN",
                    "STOP",
                    "GREEN"
            },

            {
                    "Your signal is GREEN, but a pedestrian is lawfully crossing the intersection. What should you do?",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "STOP",
                    "GREEN"
            },


            // =================================================
            // FLASHING GREEN
            // =================================================

            {
                    "You approach a FLASHING GREEN traffic signal. What should you do?",
                    "GO WITH CAUTION",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "FLASHING_GREEN"
            },

            {
                    "The traffic light is FLASHING GREEN. What is the proper action?",
                    "GO WITH CAUTION",
                    "STOP",
                    "SLOW DOWN",
                    "GO WITH CAUTION",
                    "FLASHING_GREEN"
            },

            {
                    "You see a FLASHING GREEN signal at an intersection. What should you do?",
                    "GO WITH CAUTION",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "FLASHING_GREEN"
            },

            {
                    "The signal is FLASHING GREEN and you are approaching the intersection. What should you do?",
                    "GO WITH CAUTION",
                    "STOP",
                    "GO",
                    "GO WITH CAUTION",
                    "FLASHING_GREEN"
            },

            {
                    "You are facing a FLASHING GREEN traffic signal. What should you do?",
                    "GO WITH CAUTION",
                    "STOP",
                    "SLOW DOWN",
                    "GO WITH CAUTION",
                    "FLASHING_GREEN"
            }
    };


    // =========================================================
    // ON CREATE
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_traffic_light_simulator);


        // =====================================================
        // FIND TRAFFIC LIGHT VIEWS
        // =====================================================

        redLight = findViewById(R.id.redLight);
        yellowLight = findViewById(R.id.yellowLight);
        greenLight = findViewById(R.id.greenLight);


        // =====================================================
        // FIND CAR AND STOP LINE
        // =====================================================

        car = findViewById(R.id.car);
        stopLine = findViewById(R.id.stopLine);

        // FLIP CAR DIRECTION:
        // Kung pakanaw (right) ang takbo papunta sa traffic light pero nakaharap sa pakaliwa (left) ang kotse,
        // baguhin ang orientation nito dito. Palitan ng 1f kung nakaharap na sa tama.
        car.setScaleX(-1f);


        // =====================================================
        // FIND TEXT VIEWS
        // =====================================================

        tvScenario = findViewById(R.id.tvScenario);
        tvProgress = findViewById(R.id.tvProgress);
        tvResult = findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.tvScore);


        // =====================================================
        // FIND BUTTONS
        // =====================================================

        btnStop = findViewById(R.id.btnStop);
        btnSlow = findViewById(R.id.btnSlow);
        btnGo = findViewById(R.id.btnGo);
        btnCaution = findViewById(R.id.btnCaution);
        btnNext = findViewById(R.id.btnNext);

        Button btnBack = findViewById(R.id.btnBack);


        // =====================================================
        // CREATE RANDOM QUESTION SET
        // =====================================================

        createRandomQuestionSet();


        // =====================================================
        // BACK BUTTON
        // =====================================================

        btnBack.setOnClickListener(v -> showBackConfirmation());


        // =====================================================
        // STOP BUTTON
        // =====================================================

        btnStop.setOnClickListener(v -> {

            if (!answerSelected) {

                SoundManager.playButtonClick(this);

                selectAnswerButton(btnStop);

                checkAnswer(
                        "STOP",
                        btnStop
                );
            }
        });


        // =====================================================
        // SLOW DOWN BUTTON
        // =====================================================

        btnSlow.setOnClickListener(v -> {

            if (!answerSelected) {

                SoundManager.playButtonClick(this);

                selectAnswerButton(btnSlow);

                checkAnswer(
                        "SLOW DOWN",
                        btnSlow
                );
            }
        });


        // =====================================================
        // GO BUTTON
        // =====================================================

        btnGo.setOnClickListener(v -> {

            if (!answerSelected) {

                SoundManager.playButtonClick(this);

                selectAnswerButton(btnGo);

                checkAnswer(
                        "GO",
                        btnGo
                );
            }
        });


        // =====================================================
        // GO WITH CAUTION BUTTON
        // =====================================================

        btnCaution.setOnClickListener(v -> {

            if (!answerSelected) {

                SoundManager.playButtonClick(this);

                selectAnswerButton(btnCaution);

                checkAnswer(
                        "GO WITH CAUTION",
                        btnCaution
                );
            }
        });


        // =====================================================
        // NEXT BUTTON
        // =====================================================

        btnNext.setOnClickListener(v -> {

            SoundManager.playButtonClick(this);

            nextScenario();
        });


        // =====================================================
        // LOAD FIRST SCENARIO
        // =====================================================

        loadScenario();
    }


    // =========================================================
    // CREATE RANDOM QUESTION SET
    // =========================================================

    private void createRandomQuestionSet() {

        selectedQuestions.clear();

        List<String[]> shuffledQuestions =
                new ArrayList<>();

        Collections.addAll(
                shuffledQuestions,
                questionBank
        );

        Collections.shuffle(
                shuffledQuestions
        );


        // Only 10 questions per game

        for (int i = 0; i < 10; i++) {

            selectedQuestions.add(
                    shuffledQuestions.get(i)
            );
        }
    }


    // =========================================================
    // LOAD SCENARIO
    // =========================================================

    private void loadScenario() {

        stopAllLightAnimations();


        if (scenarioNumber >= selectedQuestions.size()) {

            return;
        }


        String[] currentQuestion =
                selectedQuestions.get(
                        scenarioNumber
                );


        // =====================================================
        // SCENARIO NUMBER
        // =====================================================

        tvScenario.setText(
                "Scenario " +
                        (scenarioNumber + 1) +
                        " of " +
                        selectedQuestions.size()
        );


        // =====================================================
        // PROGRESS
        // =====================================================

        tvProgress.setText(
                "Question " +
                        (scenarioNumber + 1) +
                        " / " +
                        selectedQuestions.size()
        );


        // =====================================================
        // QUESTION
        // =====================================================

        tvResult.setText(
                currentQuestion[0]
        );


        // =====================================================
        // SCORE
        // =====================================================

        tvScore.setText(
                "Score: " + score
        );


        // =====================================================
        // RESET ANSWER
        // =====================================================

        answerSelected = false;

        btnNext.setVisibility(
                View.GONE
        );

        resetAnswerButtons();


        // =====================================================
        // RESET CAR
        // =====================================================

        car.animate().cancel();

        car.setTranslationX(0);

        car.setAlpha(1f);


        // =====================================================
        // RESET TRAFFIC LIGHT
        // =====================================================

        redLight.animate().cancel();
        yellowLight.animate().cancel();
        greenLight.animate().cancel();

        redLight.clearAnimation();
        yellowLight.clearAnimation();
        greenLight.clearAnimation();

        redLight.setAlpha(0.25f);
        yellowLight.setAlpha(0.25f);
        greenLight.setAlpha(0.25f);


        // =====================================================
        // SHOW CORRECT TRAFFIC LIGHT
        // =====================================================

        String light =
                currentQuestion[5];


        switch (light) {

            case "RED":

                showRed();

                break;


            case "YELLOW":

                showYellow();

                break;


            case "GREEN":

                showGreen();

                break;


            case "FLASHING_RED":

                redLight.setTag("FLASHING_RED");
                showFlashingRed();

                break;


            case "FLASHING_YELLOW":

                yellowLight.setTag("FLASHING_YELLOW");
                showFlashingYellow();

                break;


            case "FLASHING_GREEN":

                greenLight.setTag("FLASHING_GREEN");
                showFlashingGreen();

                break;
        }
    }

    // RESET ANSWER BUTTON COLORS

    private void resetAnswerButtons() {

        int darkBlue = Color.rgb(11, 26, 62);
        int white = Color.WHITE;

        btnStop.setTextColor(darkBlue);
        btnSlow.setTextColor(darkBlue);
        btnGo.setTextColor(darkBlue);
        btnCaution.setTextColor(darkBlue);

        btnStop.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(white)
        );

        btnSlow.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(white)
        );

        btnGo.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(white)
        );

        btnCaution.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(white)
        );
    }

    // SELECT ANSWER BUTTON

    private void selectAnswerButton(Button selectedButton) {

        resetAnswerButtons();

        selectedButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(11, 78, 216)
                )
        );

        selectedButton.setTextColor(Color.WHITE);
    }

    // CHECK ANSWER

    private void checkAnswer(
            String selectedAnswer,
            Button selectedButton
    ) {

        if (answerSelected) {

            return;
        }


        answerSelected = true;


        String[] currentQuestion =
                selectedQuestions.get(
                        scenarioNumber
                );


        String correctAnswer =
                currentQuestion[4];

        // CORRECT ANSWER

        if (selectedAnswer.equals(
                correctAnswer
        )) {

            SoundManager.playCorrectAnswer(this);

            score++;


            tvScore.setText(
                    "Score: " + score
            );


            tvResult.setText(
                    "✓ Correct!\n" +
                            getFeedback(
                                    correctAnswer
                            )
            );

            // CAR MOVEMENT

            if (correctAnswer.equals(
                    "GO"
            )) {

                moveCarFast();

            }

            else if (
                    correctAnswer.equals(
                            "SLOW DOWN"
                    )
            ) {

                moveCarSlow();

            }

            else if (
                    correctAnswer.equals(
                            "GO WITH CAUTION"
                    )
            ) {

                moveCarCautiously();

            }

            else {

                stopCar();
            }
        }

        // WRONG ANSWER

        else {

            SoundManager.playWrongAnswer(this);

            tvResult.setText(
                    "✗ Incorrect.\n" +
                            "Correct answer: " +
                            correctAnswer
            );


            stopCar();
        }

        // SHOW NEXT BUTTON

        btnNext.setVisibility(
                View.VISIBLE
        );
    }


    // FEEDBACK

    private String getFeedback(
            String answer
    ) {

        switch (answer) {

            case "STOP":

                return "You should stop and wait before proceeding.";


            case "SLOW DOWN":

                return "Slow down and proceed with caution.";


            case "GO":

                return "You may proceed when it is safe.";


            case "GO WITH CAUTION":

                return "Proceed carefully and watch for pedestrians or other road users.";


            default:

                return "Good job!";
        }
    }


    // STOP CAR

    private void stopCar() {

        car.animate().cancel();

        car.setTranslationX(0);
    }

    // MOVE CAR FAST

    private void moveCarFast() {

        float distance = Math.abs(stopLine.getX() - car.getX());


        if (distance < 150) {

            distance = 300;
        }


        ObjectAnimator animator =
                ObjectAnimator.ofFloat(
                        car,
                        "translationX",
                        0,
                        distance
                );


        animator.setDuration(
                700
        );

        animator.start();
    }

    // MOVE CAR SLOW

    private void moveCarSlow() {

        float distance = Math.abs(stopLine.getX() - car.getX());


        if (distance < 150) {

            distance = 220;
        }


        ObjectAnimator animator =
                ObjectAnimator.ofFloat(
                        car,
                        "translationX",
                        0,
                        distance
                );


        animator.setDuration(
                1500
        );

        animator.start();
    }

    // MOVE CAR WITH CAUTION

    private void moveCarCautiously() {

        float distance = Math.abs(stopLine.getX() - car.getX());


        if (distance < 150) {

            distance = 250;
        }


        ObjectAnimator animator =
                ObjectAnimator.ofFloat(
                        car,
                        "translationX",
                        0,
                        distance
                );


        animator.setDuration(
                1800
        );

        animator.start();
    }

    // NEXT SCENARIO

    private void nextScenario() {

        scenarioNumber++;


        if (
                scenarioNumber
                        < selectedQuestions.size()
        ) {

            loadScenario();

        }

        else {

            SoundManager.playQuizComplete(this);

            showFinalScore();
        }
    }

    // FINAL SCORE

    private void showFinalScore() {

        int total =
                selectedQuestions.size();


        int percentage =
                (score * 100) / total;


        Intent intent =
                new Intent(
                        TrafficLightSimulatorActivity.this,
                        ResultActivity.class
                );


        intent.putExtra(
                "score",
                score
        );


        intent.putExtra(
                "total",
                total
        );


        intent.putExtra(
                "percentage",
                percentage
        );


        intent.putExtra(
                "scenario",
                "Traffic Light"
        );


        startActivity(intent);


        finish();
    }

    // SOLID RED

    private void showRed() {

        redLight.setAlpha(1f);

        yellowLight.setAlpha(
                0.25f
        );

        greenLight.setAlpha(
                0.25f
        );
    }

    // SOLID YELLOW

    private void showYellow() {

        redLight.setAlpha(
                0.25f
        );

        yellowLight.setAlpha(
                1f
        );

        greenLight.setAlpha(
                0.25f
        );
    }


    // SOLID GREEN

    private void showGreen() {

        redLight.setAlpha(
                0.25f
        );

        yellowLight.setAlpha(
                0.25f
        );

        greenLight.setAlpha(
                1f
        );
    }

    // FLASHING RED

    private void showFlashingRed() {

        redLight.setAlpha(1f);
        yellowLight.setAlpha(0.25f);
        greenLight.setAlpha(0.25f);

        redLight.animate()
                .alpha(0.2f)
                .setDuration(500)
                .withEndAction(() -> {

                    redLight.animate()
                            .alpha(1f)
                            .setDuration(500)
                            .withEndAction(() -> {

                                if (redLight.getTag() == "FLASHING_RED") {
                                    showFlashingRed();
                                }

                            })
                            .start();

                })
                .start();
    }

    // FLASHING YELLOW

    private void showFlashingYellow() {

        redLight.setAlpha(0.25f);
        yellowLight.setAlpha(1f);
        greenLight.setAlpha(0.25f);

        yellowLight.animate()
                .alpha(0.2f)
                .setDuration(500)
                .withEndAction(() -> {

                    yellowLight.animate()
                            .alpha(1f)
                            .setDuration(500)
                            .withEndAction(() -> {

                                if (yellowLight.getTag() == "FLASHING_YELLOW") {
                                    showFlashingYellow();
                                }

                            })
                            .start();

                })
                .start();
    }

    // FLASHING GREEN

    private void showFlashingGreen() {

        redLight.setAlpha(0.25f);
        yellowLight.setAlpha(0.25f);
        greenLight.setAlpha(1f);

        greenLight.animate()
                .alpha(0.2f)
                .setDuration(500)
                .withEndAction(() -> {

                    greenLight.animate()
                            .alpha(1f)
                            .setDuration(500)
                            .withEndAction(() -> {

                                if (greenLight.getTag() == "FLASHING_GREEN") {
                                    showFlashingGreen();
                                }

                            })
                            .start();

                })
                .start();
    }

    // STOP ALL TRAFFIC LIGHT ANIMATIONS

    private void stopAllLightAnimations() {

        redLight.setTag("STOP");
        yellowLight.setTag("STOP");
        greenLight.setTag("STOP");

        redLight.animate().cancel();
        yellowLight.animate().cancel();
        greenLight.animate().cancel();

        redLight.clearAnimation();
        yellowLight.clearAnimation();
        greenLight.clearAnimation();

        redLight.setAlpha(0.25f);
        yellowLight.setAlpha(0.25f);
        greenLight.setAlpha(0.25f);
    }

    // BACK CONFIRMATION

    private void showBackConfirmation() {

        new AlertDialog.Builder(this)

                .setTitle(
                        "Are you sure you want to go back?"
                )

                .setMessage(
                        "Your current progress will not be saved."
                )

                .setNegativeButton(
                        "Cancel",
                        null
                )

                .setPositiveButton(
                        "Back",
                        (dialog, which) -> finish()
                )

                .show();
    }

    // PHYSICAL BACK BUTTON

    @Override
    public void onBackPressed() {

        showBackConfirmation();
    }
}