package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;

/**
 * Diese Activity zeigt Übungen für die Rückenmuskulatur.
 * Aufbau ist identisch wie ArmsTrainingActivity.
 */

public class BackTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_training); // muss exakt so heißen wie deine XML-Datei

        // IDs müssen exakt zu den Button-IDs im Layout passen
        Button btnPullups = findViewById(R.id.button_pullup);
        Button btnLat = findViewById(R.id.button_lat);
        Button btnRow = findViewById(R.id.button_row);

        btnPullups.setOnClickListener(v -> openExercise("Klimmzüge"));
        btnLat.setOnClickListener(v -> openExercise("Latziehen"));
        btnRow.setOnClickListener(v -> openExercise("Rudern"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // beendet die Activity, kehrt zur vorherigen zurück
        return true;
    }

    private void openExercise(String name) {
        Intent intent = new Intent(this, ExerciseInputActivity.class);
        intent.putExtra("exercise_name", name);
        intent.putExtra("exercise_category", "Rücken");
        startActivity(intent);
    }

}
