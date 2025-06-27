package com.example.gymtrack.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymtrack.R;
import com.example.gymtrack.view.Uebungen.ArmsTrainingActivity;
import com.example.gymtrack.view.Uebungen.BackTrainingActivity;
import com.example.gymtrack.view.Uebungen.ChestTrainingActivity;
import com.example.gymtrack.view.Uebungen.LegsTrainingActivity;
import com.example.gymtrack.view.Uebungen.ShoulderTrainingActivity;

/**
 * Startseite der App.
 * Von hier wählt der Nutzer die gewünschte Muskelgruppe aus.
 * Jeder Button führt zu einer eigenen Activity mit den passenden Übungen.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Layout mit Kategorie-Buttons

        // Buttons für jede Muskelgruppe
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonArms = findViewById(R.id.button_arms);
        Button buttonShoulders = findViewById(R.id.button_shoulders);
        Button buttonLegs = findViewById(R.id.button_legs);
        Button buttonChest = findViewById(R.id.button_chest);

        // ➤ Klickaktionen – starten die entsprechende Activity
        buttonChest.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChestTrainingActivity.class);
            startActivity(intent);
        });

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, BackTrainingActivity.class);
            startActivity(intent);
        });

        buttonArms.setOnClickListener(v -> {
            Intent intent = new Intent(this, ArmsTrainingActivity.class);
            startActivity(intent);
        });

        buttonShoulders.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShoulderTrainingActivity.class);
            startActivity(intent);
        });

        buttonLegs.setOnClickListener(v -> {
            Intent intent = new Intent(this, LegsTrainingActivity.class);
            startActivity(intent);
        });
    }
}
