package com.example.appdev;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class SoundManager {

    // Global method para i-play ang kahit anong sound file
    public static void playSound(Context context, int soundResId) {
        SharedPreferences preferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        boolean isSoundEnabled = preferences.getBoolean("sound_enabled", true);

        // Kapag naka-ON ang Sound toggle sa Settings, saka lang patutunugin
        if (isSoundEnabled) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResId);
            if (mediaPlayer != null) {
                mediaPlayer.setOnCompletionListener(MediaPlayer::release);
                mediaPlayer.start();
            }
        }
    }

    // Helper methods para mas madaling tawagin sa Quiz Activities
    public static void playButtonClick(Context context) {
        playSound(context, R.raw.button_click);
    }

    public static void playCorrectAnswer(Context context) {
        playSound(context, R.raw.quiz_correct);
    }

    public static void playWrongAnswer(Context context) {
        playSound(context, R.raw.quiz_wrong);
    }

    public static void playQuizComplete(Context context) {
        playSound(context, R.raw.quiz_complete);
    }
}